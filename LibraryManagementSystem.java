import java.util.Scanner;

public class LibraryManagementSystem {
    static class Node {
        String title,author,genre,status;
        int id;
        Node prev,next;
        Node(String t,String a,String g,int i,String s) { title=t; author=a; genre=g; id=i; status=s; }
    }

    Node head,tail;

    void add(String t,String a,String g,int id,String s,int pos) {
        Node x=new Node(t,a,g,id,s);
        if(head==null) { head=tail=x; return; }
        if(pos<=1) { x.next=head; head.prev=x; head=x; return; }
        Node p=head;
        for(int i=1;i<pos-1 && p.next!=null;i++) p=p.next;
        x.next=p.next; x.prev=p;
        if(p.next!=null) p.next.prev=x; else tail=x;
        p.next=x;
    }

    void remove(int id) {
        Node p=find(id); if(p==null) return;
        if(p.prev!=null) p.prev.next=p.next; else head=p.next;
        if(p.next!=null) p.next.prev=p.prev; else tail=p.prev;
    }

    Node find(int id) {
        for(Node p=head;p!=null;p=p.next) if(p.id==id) return p;
        return null;
    }

    void search(String key,boolean byTitle) {
        boolean found=false;
        for(Node p=head;p!=null;p=p.next) {
            if(byTitle?p.title.equalsIgnoreCase(key):p.author.equalsIgnoreCase(key)) { show(p); found=true; }
        }
        if(!found) System.out.println("Not found");
    }

    void update(int id,String s) { Node p=find(id); if(p!=null) p.status=s; }
    void show(Node p) { System.out.println(p.title+" "+p.author+" "+p.genre+" "+p.id+" "+p.status); }
    void forward() { for(Node p=head;p!=null;p=p.next) show(p); }
    void reverse() { for(Node p=tail;p!=null;p=p.prev) show(p); }
    int count() { int n=0; for(Node p=head;p!=null;p=p.next) n++; return n; }

    public static void main(String[] args) {
        LibraryManagementSystem l=new LibraryManagementSystem(); Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1 Add  2 Remove  3 Search title  4 Search author  5 Update status  6 Forward  7 Reverse  8 Count  0 Exit");
            int c=sc.nextInt(); if(c==0) break;
            if(c==1) { String t=sc.next(),a=sc.next(),g=sc.next(); int id=sc.nextInt(); String s=sc.next(); int p=sc.nextInt(); l.add(t,a,g,id,s,p); }
            else if(c==2) l.remove(sc.nextInt());
            else if(c==3) l.search(sc.next(),true);
            else if(c==4) l.search(sc.next(),false);
            else if(c==5) { int id=sc.nextInt(); String s=sc.next(); l.update(id,s); }
            else if(c==6) l.forward();
            else if(c==7) l.reverse();
            else if(c==8) System.out.println(l.count());
        }
        sc.close();
    }
}
