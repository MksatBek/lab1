package ru.spbstu.telematics.java;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;



class HashSet<T> implements Iterable<T> {

    class Node<T> {
        T data;
        int hash;
        Node<T> next;

        Node(T data) {
            this.data = data;
            hash = data.hashCode();
            next = null;
        }
    }



    @Override
    public Iterator iterator(){
        return new Iterator();
    }


    public class Iterator implements java.util.Iterator<T>{

        Node<T> next;
        Node<T> current;
        Node<T> last;
        int current_bucket;
        Iterator(){

            Node<T> temp_node;
            for(int i=0;i<capacity;i++) {
                temp_node = buckets.get(i);
                if (temp_node != null) {
                    current = temp_node;
                    current_bucket = i;
                    return;
                }
            }
            current=null;
            next=null;

        }

        public T next() throws NoSuchElementException {
            if(current==null){
                throw new NoSuchElementException();
            }
            last=current;
            Node<T> temp_node=current;
            temp_node = buckets.get(current_bucket);
            if(temp_node.next!=null){
                current=temp_node.next;
                return temp_node.data;
            }

            for(int i=current_bucket+1;i<capacity;i++){
                if(buckets.get(i)!=null){
                    current=buckets.get(i);
                    current_bucket=i;
                    return temp_node.data;
                }
            }
            throw new NoSuchElementException();
        }


        @Override
        public boolean hasNext() {
            if(current==null){
                throw new NoSuchElementException();
            }
            Node<T> temp_node;
            temp_node = buckets.get(current_bucket);
            if(temp_node.next!=null){
                return true;
            }

            for(int i=current_bucket;i<capacity;i++){
                if(buckets.get(i)!=null){
                    return true;
                }
            }
            return false;
        }

        public void remove(){
            HashSet.this.remove(last.data);
        }

    }






    ArrayList<Node<T>> buckets;

    int capacity=0;
    int size=0;
    HashSet(){
        buckets = new ArrayList<>(8);
        for(int i=0;i<8;i++){
            buckets.add(null);
        }
        size=0;
        capacity=8;
    }



    HashSet(int capacity){
        buckets = new ArrayList<>(capacity);
        for(int i=0;i<capacity;i++){
            buckets.add(null);
        }
        size=0;
        this.capacity = capacity;
    }

    public boolean add(T data){
        int hash = data.hashCode()%capacity;
        if(buckets.get(hash)==null){
            buckets.set(hash,new Node(data));
            size++;
            return true;
        }

        Node<T> temp = buckets.get(hash);
        if(temp.data == data){
            return false;
        }
        while(temp.next != null) {
            if(temp.data == data){
                return false;
            }
            temp = temp.next;
        }
        temp.next = new Node(data);
        size++;
        if(size>0.7*capacity){
            this.expand();
        }
        return true;
    }

    boolean isEmpty(){
        if(size==0){
            return true;
        }
        return false;
    }

    int size(){
        return size;
    }


    boolean contains(T data){
        int hash = data.hashCode()%capacity;
        Node<T> temp = buckets.get(hash);
        if(temp.data == data){
            return true;
        }
        Node<T> prev = temp;
        while(temp.next != null) {
            if(temp.data == data){
                return true;
            }
            prev = temp;
            temp = temp.next;
        }
        if(temp.data == data){
            return true;
        }
        return false;
    }



    public boolean remove(T data){
        int hash = data.hashCode()%capacity;
        Node<T> temp = buckets.get(hash);
        if(temp.data == data){
            buckets.set(hash,temp.next);
            size--;

            return true;
        }
        Node<T> prev = temp;
        while(temp.next != null) {
            if(temp.data == data){
               prev.next = temp.next;
                size--;

                return true;
            }
            prev = temp;
            temp = temp.next;
        }
        if(temp.data == data){
            prev.next = null;
            size--;
            return true;
        }
        return false;

    }

    // расширяем, когда таблица занята на 70%
    void expand(){
        int newCapacity=this.capacity*2;
        HashSet<T> temp_set = new HashSet<>(newCapacity);

        Node<T> temp_node;
        for(int i=0;i<this.capacity;i++) {
            temp_node = buckets.get(i);
            if (temp_node != null) {
                while (temp_node.next != null) {
                    temp_set.add(temp_node.data);
                    temp_node = temp_node.next;
                }
                temp_set.add(temp_node.data);
            }
        }

        this.capacity = temp_set.capacity;
        this.buckets = temp_set.buckets;
        this.size = temp_set.size;

        }


    }














 class Main {

    public static void main(String[] args) {


    }


}
