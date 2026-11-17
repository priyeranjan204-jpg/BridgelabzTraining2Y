import java.util.ArrayList;
import java.util.Scanner;

public class SocialMediaFriendConnections {
    static class User {
        int id,age;
        String name;
        ArrayList<Integer> friends=new ArrayList<>();
        User next;
        User(int i,String n,int a) { id=i; name=n; age=a; }
    }

    User head;

    void addUser(int id,String name,int age) {
        if(find(id)!=null) return;
        User x=new User(id,name,age);
        if(head==null) head=x;
        else { User p=head; while(p.next!=null) p=p.next; p.next=x; }
    }

    User find(int id) {
        for(User p=head;p!=null;p=p.next) if(p.id==id) return p;
        return null;
    }

    void search(String key,boolean byId) {
        boolean found=false;
        for(User p=head;p!=null;p=p.next) {
            if(byId?p.id==Integer.parseInt(key):p.name.equalsIgnoreCase(key)) {
                System.out.println(p.id+" "+p.name+" "+p.age); found=true;
            }
        }
        if(!found) System.out.println("Not found");
    }

    void connect(int a,int b) {
        User x=find(a),y=find(b);
        if(x==null || y==null || a==b) return;
        if(!x.friends.contains(b)) x.friends.add(b);
        if(!y.friends.contains(a)) y.friends.add(a);
    }

    void disconnect(int a,int b) {
        User x=find(a),y=find(b);
        if(x!=null) x.friends.remove(Integer.valueOf(b));
        if(y!=null) y.friends.remove(Integer.valueOf(a));
    }

    void display(int id) {
        User x=find(id);
        if(x==null) { System.out.println("User not found"); return; }
        for(int f:x.friends) System.out.println(f);
    }

    void mutual(int a,int b) {
        User x=find(a),y=find(b);
        if(x==null || y==null) return;
        for(int f:x.friends) if(y.friends.contains(f)) System.out.println(f);
    }

    void counts() {
        for(User p=head;p!=null;p=p.next) System.out.println(p.name+": "+p.friends.size());
    }

    public static void main(String[] args) {
        SocialMediaFriendConnections s=new SocialMediaFriendConnections(); Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("1 Add user  2 Add connection  3 Remove connection  4 Mutual friends  5 Display friends  6 Search ID  7 Search name  8 Friend counts  0 Exit");
            int c=sc.nextInt(); if(c==0) break;
            if(c==1) s.addUser(sc.nextInt(),sc.next(),sc.nextInt());
            else if(c==2) s.connect(sc.nextInt(),sc.nextInt());
            else if(c==3) s.disconnect(sc.nextInt(),sc.nextInt());
            else if(c==4) s.mutual(sc.nextInt(),sc.nextInt());
            else if(c==5) s.display(sc.nextInt());
            else if(c==6) s.search(sc.next(),true);
            else if(c==7) s.search(sc.next(),false);
            else if(c==8) s.counts();
        }
        sc.close();
    }
}
