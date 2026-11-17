import java.util.Scanner;

public class MovieManagementSystem {
    static class Node {
        String title, director;
        int year;
        double rating;
        Node prev,next;
        Node(String t,String d,int y,double r) { title=t; director=d; year=y; rating=r; }
    }

    Node head,tail;

    void add(String t,String d,int y,double r,int pos) {
        Node x=new Node(t,d,y,r);
        if(head==null) { head=tail=x; return; }
        if(pos<=1) { x.next=head; head.prev=x; head=x; return; }
        Node p=head;
        for(int i=1;i<pos-1 && p.next!=null;i++) p=p.next;
        x.next=p.next; x.prev=p;
        if(p.next!=null) p.next.prev=x; else tail=x;
        p.next=x;
    }

    void remove(String t) {
        Node p=head;
        while(p!=null && !p.title.equalsIgnoreCase(t)) p=p.next;
        if(p==null) return;
        if(p.prev!=null) p.prev.next=p.next; else head=p.next;
        if(p.next!=null) p.next.prev=p.prev; else tail=p.prev;
    }

    void search(String key,boolean byDirector) {
        Node p=head; boolean found=false;
        while(p!=null) {
            if(byDirector?p.director.equalsIgnoreCase(key):Double.compare(p.rating,Double.parseDouble(key))==0) {
                show(p); found=true;
            }
            p=p.next;
        }
        if(!found) System.out.println("Not found");
    }

    void update(String t,double r) {
        Node p=head;
        while(p!=null) {
            if(p.title.equalsIgnoreCase(t)) { p.rating=r; return; }
            p=p.next;
        }
    }

    void show(Node p) { System.out.println(p.title+" | "+p.director+" | "+p.year+" | "+p.rating); }
    void forward() { for(Node p=head;p!=null;p=p.next) show(p); }
    void reverse() { for(Node p=tail;p!=null;p=p.prev) show(p); }

    public static void main(String[] args) {
        MovieManagementSystem m=new MovieManagementSystem();
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1 Add  2 Remove title  3 Search director  4 Search rating  5 Forward  6 Reverse  7 Update rating  0 Exit");
            int ch=sc.nextInt(); if(ch==0) break;
            if(ch==1) {
                String t=sc.next(),d=sc.next(); int y=sc.nextInt(); double r=sc.nextDouble(); int p=sc.nextInt();
                m.add(t,d,y,r,p);
            } else if(ch==2) m.remove(sc.next());
            else if(ch==3) m.search(sc.next(),true);
            else if(ch==4) m.search(sc.next(),false);
            else if(ch==5) m.forward();
            else if(ch==6) m.reverse();
            else if(ch==7) { String t=sc.next(); double r=sc.nextDouble(); m.update(t,r); }
        }
        sc.close();
    }
}
