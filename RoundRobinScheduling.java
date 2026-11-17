import java.util.Scanner;

public class RoundRobinScheduling {
    static class Node {
        int id,burst,remain,priority;
        Node next;
        Node(int i,int b,int p) { id=i; burst=b; remain=b; priority=p; }
    }

    Node head,tail;

    void add(int id,int burst,int priority) {
        Node x=new Node(id,burst,priority);
        if(head==null) { head=tail=x; x.next=x; }
        else { x.next=head; tail.next=x; tail=x; }
    }

    void display() {
        if(head==null) { System.out.println("Empty"); return; }
        Node p=head;
        do { System.out.print("P"+p.id+"("+p.remain+") "); p=p.next; } while(p!=head);
        System.out.println();
    }

    void run(int tq) {
        if(head==null || tq<=0) return;
        int n=0;
        Node p=head;
        do { n++; p=p.next; } while(p!=head);
        int[] ids=new int[n],wait=new int[n],turn=new int[n],burst=new int[n];
        p=head; for(int i=0;i<n;i++) { ids[i]=p.id; burst[i]=p.burst; p=p.next; }
        int time=0,done=0;
        while(head!=null) {
            Node cur=head;
            do {
                if(cur.remain>0) {
                    int use=Math.min(tq,cur.remain);
                    cur.remain-=use; time+=use;
                    if(cur.remain==0) {
                        for(int i=0;i<n;i++) if(ids[i]==cur.id) { turn[i]=time; wait[i]=turn[i]-burst[i]; break; }
                        done++;
                    }
                }
                cur=cur.next;
            } while(head!=null && cur!=head);
            display();
            if(done==n) break;
            Node prev=tail;
            while(prev.next!=head) prev=prev.next;
            Node q=head;
            do {
                if(q.remain==0) {
                    if(q==head) head=q.next;
                    if(q==tail) tail=prev;
                    prev.next=q.next;
                    break;
                }
                prev=q; q=q.next;
            } while(q!=head);
            if(head==null) break;
            tail.next=head;
        }
        double aw=0,at=0;
        for(int i=0;i<n;i++) { aw+=wait[i]; at+=turn[i]; }
        System.out.println("Average waiting time: "+aw/n);
        System.out.println("Average turnaround time: "+at/n);
        head=tail=null;
    }

    public static void main(String[] args) {
        RoundRobinScheduling r=new RoundRobinScheduling(); Scanner sc=new Scanner(System.in);
        System.out.print("Number of processes: "); int n=sc.nextInt();
        for(int i=0;i<n;i++) { System.out.print("Process ID, burst time, priority: "); r.add(sc.nextInt(),sc.nextInt(),sc.nextInt()); }
        System.out.print("Time quantum: "); int tq=sc.nextInt();
        r.run(tq); sc.close();
    }
}
