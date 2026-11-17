import java.util.Scanner;

public class TicketReservationSystem {
    static class Node {
        int id,seat;
        String customer,movie,time;
        Node next;
        Node(int i,String c,String m,int s,String t) { id=i; customer=c; movie=m; seat=s; time=t; }
    }

    Node head,tail;
    int size;

    void add(int id,String c,String m,int seat,String time) {
        Node x=new Node(id,c,m,seat,time);
        if(head==null) { head=tail=x; x.next=head; }
        else { tail.next=x; tail=x; tail.next=head; }
        size++;
    }

    void remove(int id) {
        if(head==null) return;
        Node p=head,prev=tail;
        do {
            if(p.id==id) {
                if(head==tail) head=tail=null;
                else {
                    prev.next=p.next;
                    if(p==head) head=p.next;
                    if(p==tail) tail=prev;
                    tail.next=head;
                }
                size--; return;
            }
            prev=p; p=p.next;
        } while(p!=head);
    }

    void search(String key,boolean byCustomer) {
        if(head==null) return;
        boolean found=false; Node p=head;
        do {
            if(byCustomer?p.customer.equalsIgnoreCase(key):p.movie.equalsIgnoreCase(key)) {
                show(p); found=true;
            }
            p=p.next;
        } while(p!=head);
        if(!found) System.out.println("Not found");
    }

    void display() {
        if(head==null) { System.out.println("No bookings"); return; }
        Node p=head;
        do { show(p); p=p.next; } while(p!=head);
    }

    void show(Node p) { System.out.println(p.id+" "+p.customer+" "+p.movie+" "+p.seat+" "+p.time); }

    public static void main(String[] args) {
        TicketReservationSystem t=new TicketReservationSystem(); Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1 Book ticket  2 Cancel ticket  3 Display  4 Search customer  5 Search movie  6 Count  0 Exit");
            int c=sc.nextInt(); if(c==0) break;
            if(c==1) { int id=sc.nextInt(); String n=sc.next(),m=sc.next(); int seat=sc.nextInt(); String time=sc.next(); t.add(id,n,m,seat,time); }
            else if(c==2) t.remove(sc.nextInt());
            else if(c==3) t.display();
            else if(c==4) t.search(sc.next(),true);
            else if(c==5) t.search(sc.next(),false);
            else if(c==6) System.out.println(t.size);
        }
        sc.close();
    }
}
