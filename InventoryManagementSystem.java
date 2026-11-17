import java.util.Scanner;

public class InventoryManagementSystem {
    static class Node {
        String name;
        int id,qty;
        double price;
        Node next;
        Node(String n,int i,int q,double p) { name=n; id=i; qty=q; price=p; }
    }

    Node head;

    void add(String n,int id,int q,double p,int pos) {
        Node x=new Node(n,id,q,p);
        if(pos<=1 || head==null) { x.next=head; head=x; return; }
        Node a=head;
        for(int i=1;i<pos-1 && a.next!=null;i++) a=a.next;
        x.next=a.next; a.next=x;
    }

    void remove(int id) {
        if(head==null) return;
        if(head.id==id) { head=head.next; return; }
        Node p=head;
        while(p.next!=null && p.next.id!=id) p=p.next;
        if(p.next!=null) p.next=p.next.next;
    }

    void update(int id,int q) {
        Node p=find(id);
        if(p!=null) p.qty=q;
    }

    Node find(int id) {
        for(Node p=head;p!=null;p=p.next) if(p.id==id) return p;
        return null;
    }

    void search(String key,boolean byId) {
        boolean found=false;
        for(Node p=head;p!=null;p=p.next) {
            if(byId? p.id==Integer.parseInt(key):p.name.equalsIgnoreCase(key)) {
                show(p); found=true;
            }
        }
        if(!found) System.out.println("Not found");
    }

    double total() {
        double sum=0;
        for(Node p=head;p!=null;p=p.next) sum+=p.price*p.qty;
        return sum;
    }

    void sort(boolean byName,boolean asc) {
        for(Node i=head;i!=null;i=i.next) {
            for(Node j=i.next;j!=null;j=j.next) {
                int cmp=byName?i.name.compareToIgnoreCase(j.name):Double.compare(i.price,j.price);
                if((asc && cmp>0)||(!asc && cmp<0)) {
                    String n=i.name; i.name=j.name; j.name=n;
                    int id=i.id; i.id=j.id; j.id=id;
                    int q=i.qty; i.qty=j.qty; j.qty=q;
                    double pr=i.price; i.price=j.price; j.price=pr;
                }
            }
        }
    }

    void show(Node p) { System.out.println(p.name+" "+p.id+" "+p.qty+" "+p.price); }
    void display() { for(Node p=head;p!=null;p=p.next) show(p); }

    public static void main(String[] args) {
        InventoryManagementSystem s=new InventoryManagementSystem(); Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1 Add  2 Remove  3 Update quantity  4 Search ID  5 Search name  6 Total value  7 Sort name  8 Sort price  9 Display  0 Exit");
            int c=sc.nextInt(); if(c==0) break;
            if(c==1) { String n=sc.next(); int id=sc.nextInt(),q=sc.nextInt(); double p=sc.nextDouble(); int pos=sc.nextInt(); s.add(n,id,q,p,pos); }
            else if(c==2) s.remove(sc.nextInt());
            else if(c==3) { int id=sc.nextInt(),q=sc.nextInt(); s.update(id,q); }
            else if(c==4) s.search(sc.next(),true);
            else if(c==5) s.search(sc.next(),false);
            else if(c==6) System.out.println(s.total());
            else if(c==7 || c==8) { int order=sc.nextInt(); s.sort(c==7,order==1); }
            else if(c==9) s.display();
        }
        sc.close();
    }
}
