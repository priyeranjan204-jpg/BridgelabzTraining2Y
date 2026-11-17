import java.util.Scanner;

public class TaskScheduler {
    static class Node {
        int id,priority;
        String name,date;
        Node next;
        Node(int i,String n,int p,String d) { id=i; name=n; priority=p; date=d; }
    }

    Node head,tail,current;

    void add(int id,String n,int p,String d,int pos) {
        Node x=new Node(id,n,p,d);
        if(head==null) { head=tail=current=x; x.next=x; return; }
        if(pos<=1) { x.next=head; head=x; tail.next=head; return; }
        Node q=head;
        for(int i=1;i<pos-1 && q.next!=head;i++) q=q.next;
        x.next=q.next; q.next=x;
        if(q==tail) tail=x;
        tail.next=head;
    }

    void remove(int id) {
        if(head==null) return;
        Node p=head,prev=tail;
        do {
            if(p.id==id) {
                if(p==head && p==tail) { head=tail=current=null; return; }
                prev.next=p.next;
                if(p==head) head=p.next;
                if(p==tail) tail=prev;
                if(current==p) current=p.next;
                tail.next=head; return;
            }
            prev=p; p=p.next;
        } while(p!=head);
    }

    void nextTask() {
        if(current==null) System.out.println("No tasks");
        else { show(current); current=current.next; }
    }

    void search(int priority) {
        if(head==null) return;
        Node p=head;
        do { if(p.priority==priority) show(p); p=p.next; } while(p!=head);
    }

    void display() {
        if(head==null) return;
        Node p=head;
        do { show(p); p=p.next; } while(p!=head);
    }

    void show(Node p) { System.out.println(p.id+" "+p.name+" "+p.priority+" "+p.date); }

    public static void main(String[] args) {
        TaskScheduler t=new TaskScheduler(); Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1 Add  2 Remove  3 Current/Next  4 Display  5 Search priority  0 Exit");
            int c=sc.nextInt(); if(c==0) break;
            if(c==1) { int id=sc.nextInt(); String n=sc.next(); int p=sc.nextInt(); String d=sc.next(); int pos=sc.nextInt(); t.add(id,n,p,d,pos); }
            else if(c==2) t.remove(sc.nextInt());
            else if(c==3) t.nextTask();
            else if(c==4) t.display();
            else if(c==5) t.search(sc.nextInt());
        }
        sc.close();
    }
}
