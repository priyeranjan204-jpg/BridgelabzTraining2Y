import java.util.Scanner;

public class TextEditorUndoRedo {
    static class Node {
        String text;
        Node prev,next;
        Node(String s) { text=s; }
    }

    Node head,tail,current;
    int size,limit=10;

    void add(String s) {
        if(current!=null) {
            current.next=null;
            tail=current;
            while(size>0 && tail!=null) {
                int count=0; for(Node p=head;p!=null;p=p.next) count++;
                size=count; break;
            }
        }
        Node x=new Node(s);
        if(head==null) head=tail=current=x;
        else { tail.next=x; x.prev=tail; tail=current=x; }
        size++;
        while(size>limit) { head=head.next; head.prev=null; size--; }
    }

    void undo() { if(current!=null && current.prev!=null) current=current.prev; }
    void redo() { if(current!=null && current.next!=null) current=current.next; }
    void show() { System.out.println(current==null?"":current.text); }

    public static void main(String[] args) {
        TextEditorUndoRedo e=new TextEditorUndoRedo(); Scanner sc=new Scanner(System.in);
        e.add("");
        while(true) {
            System.out.println("1 Type/replace text  2 Undo  3 Redo  4 Current text  0 Exit");
            int c=sc.nextInt(); sc.nextLine(); if(c==0) break;
            if(c==1) e.add(sc.nextLine());
            else if(c==2) e.undo();
            else if(c==3) e.redo();
            else if(c==4) e.show();
        }
        sc.close();
    }
}
