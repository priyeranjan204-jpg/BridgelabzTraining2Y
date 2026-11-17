import java.util.Scanner;

public class StudentRecordManagement {
    static class Node {
        int roll, age;
        String name, grade;
        Node next;
        Node(int r, String n, int a, String g) {
            roll=r; name=n; age=a; grade=g;
        }
    }

    Node head;

    void add(int r, String n, int a, String g, int pos) {
        Node x=new Node(r,n,a,g);
        if(pos<=1 || head==null) {
            x.next=head; head=x; return;
        }
        Node p=head;
        for(int i=1;i<pos-1 && p.next!=null;i++) p=p.next;
        x.next=p.next; p.next=x;
    }

    void delete(int r) {
        if(head==null) return;
        if(head.roll==r) { head=head.next; return; }
        Node p=head;
        while(p.next!=null && p.next.roll!=r) p=p.next;
        if(p.next!=null) p.next=p.next.next;
    }

    Node search(int r) {
        Node p=head;
        while(p!=null) {
            if(p.roll==r) return p;
            p=p.next;
        }
        return null;
    }

    void update(int r, String g) {
        Node p=search(r);
        if(p!=null) p.grade=g;
    }

    void display() {
        Node p=head;
        while(p!=null) {
            System.out.println(p.roll+" "+p.name+" "+p.age+" "+p.grade);
            p=p.next;
        }
    }

    public static void main(String[] args) {
        StudentRecordManagement s=new StudentRecordManagement();
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1 Add  2 Delete  3 Search  4 Display  5 Update grade  0 Exit");
            int ch=sc.nextInt();
            if(ch==0) break;
            if(ch==1) {
                System.out.print("Roll, name, age, grade, position: ");
                int r=sc.nextInt(); String n=sc.next(); int a=sc.nextInt(); String g=sc.next(); int p=sc.nextInt();
                s.add(r,n,a,g,p);
            } else if(ch==2) {
                s.delete(sc.nextInt());
            } else if(ch==3) {
                Node x=s.search(sc.nextInt());
                System.out.println(x==null?"Not found":x.roll+" "+x.name+" "+x.age+" "+x.grade);
            } else if(ch==4) s.display();
            else if(ch==5) {
                int r=sc.nextInt(); String g=sc.next(); s.update(r,g);
            }
        }
        sc.close();
    }
}
