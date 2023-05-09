import java.util.HashMap;

public class Cache <T>{

    private class Node{

        private int key;
        private T data;

        private Node next, prev;

        public Node(int key, T data){

            this.data = data;
            this.key = key;

            this.next = this.prev = null;

        }

    }

    private HashMap<Integer,Node> map;

    private Node head, tail;

    private int size;

    public Cache(int capacity){

        this.tail = this.head = null;

        this.size = capacity;

        this.map = new HashMap<>();

    }

    public String toString(){

        StringBuilder str = new StringBuilder();

        Node temp = head;

        while(temp != null){

            str.append(temp.data+" ");

            temp = temp.next;

        }

        String s = new String(str);

        return s;

    }


    public T get(int key){

        if(map.get(key) == null) return null;

        Node temp = map.get(key);

        if(temp == head) return head.data;

        if(temp == tail){

            T data = tail.data;

            Node prevTail = tail.prev;

            tail.prev.next = null;

            tail.prev = null;

            tail.next = head;

            head.prev = tail;

            head = tail;

            tail = prevTail;

            return data;

        }

        temp.prev.next = temp.next;

        temp.next.prev = temp.prev;

        temp.prev = temp.next = null;

        temp.next = head;

        head.prev = temp;

        head = temp;

        return temp.data;

    }

    public void set(int key, T data){

        if(map.get(key) == null){

            if(head == null){

                head = new Node(key, data);
                tail = head;

            }

            else{

                if(map.size() == size){

                    map.remove(tail.key);

                    if(tail.prev != null) tail.prev.next = null;

                    if(tail == head) head = null;

                    tail = tail.prev;

                }

                Node newNode = new Node(key, data);

                if(head != null){

                    newNode.next = head;

                    head.prev = newNode;

                    head = newNode;

                }


            }

            if(head != null) map.put(head.key,head);

        }

        else{

            Node temp = map.get(key);

           if(temp == head) {

              head.data = data;

              return;
            
           }

           if(temp == tail){

             Node prevTail = tail.prev;

             tail.data = data;

             if(tail.prev != null) tail.prev.next = null;

             tail.prev = null;

             tail.next = head;

             head.prev = tail;

             head = tail;

             tail = prevTail;

             return;

           }

           temp.data = data;

           temp.prev.next = temp.next;

           temp.next.prev = temp.prev;

           temp.prev = temp.next = null;

           temp.next = head;

           head.prev = temp;

           head = temp;

        }

    }

    public static void main(String[] args) {

        Cache<Integer> cache = new Cache<>(2);

        cache.set(12, 25);
        cache.set(0, 52);
        cache.get(12);

        System.out.println(cache);
        
    }





    

}