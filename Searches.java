import java.util.*;

public class Searches {
    private static int counter=0;
    static int[][] goal=new int[4][4];
    public static void main(String[] args){

        //ArrayList<String> str=new ArrayList<>();
        int i,j;
        int[][] initial=new int[4][4];
        for(i=0;i<4;i++){
            for(j=0;j<4;j++)
            {
                initial[i][j]=0;
            }
        }
        initial[0][1]=65;
        initial[1][2]=66;
        initial[1][3]=67;
        initial[1][1]=42;

        for(i=0;i<4;i++){
            for(j=0;j<4;j++)
            {
                goal[i][j]=0;
            }
        }
        goal[1][1]=65;
        goal[1][2]=66;
        goal[1][3]=67;
        goal[3][3]=42;

        Stack s= doAStar(initial,goal);

        while(!s.empty()) {
            Node n= (Node) s.pop();
            System.out.print(n.getMove()+" ");
        }

//        while(!s.empty())
//        {
//            Node n= (Node) s.pop();
//            System.out.print(n.getMove());
//            for(i=0; i<n.getState().length;i++)
//            {
//                if(i!=0)
//                {System.out.print("    ");}
//                for(j=0; j<n.getState().length;j++)
//                {
//                    if(n.getState()[j][i]==0){
//                        System.out.print("[ ]");
//                    }else{
//                        System.out.print("["+(char) n.getState()[j][i]+"]");
//                    }
//                }
//
//                System.out.println(" ");
//            }
//            System.out.println(" ");
//
//        }


    }
    public static List<String> getRandom(){
        List<String> move=  Arrays.asList("up","down","left","right");
        Collections.shuffle(move);
        return move;
    }
    public static void showState(Node n)
    {
        int i,j;
        for(i=0; i<n.getState().length;i++)
            {
                if(i!=0)
                {System.out.print("    ");}
                for(j=0; j<n.getState().length;j++)
                {
                    if(n.getState()[j][i]==0){
                        System.out.print("[ ]");
                    }else{
                        System.out.print("["+(char) n.getState()[j][i]+"]");
                    }
                }

                System.out.println(" ");
            }
    }
    public static Stack getMoves(Node n) {
        Stack<Node> mv=new Stack<>();
        while (n.getParent()!=null)
        {
            mv.push(n);
            n=n.getParent();
        }
        return mv;
    }

    public static List<Node> expandNode(Node parent, Boolean ok)
    {
        List<Node> list=new ArrayList<>();
        List<String> moves;
        if(ok==true){
            moves= getRandom();
        } else{
            moves= Arrays.asList("up","down","left","right");
            }

        for(int i=0;i<4;i++)
        {
            if(parent.calculateState(parent,moves.get(i))!=null) {
                list.add(new Node(parent, moves.get(i), parent.getDepth() + 1, 1,goal));
            }
        }
        return list;
    }
    public static Boolean goalState(Node current, int[][] goal)
    {
        for(int i=0;i<current.getState().length;i++)
        {
            for(int j=0;j<current.getState().length;j++)
            {
                if ((current.getState()[i][j]!=0&&current.getState()[i][j]!=42&&current.getState()[i][j]!=goal[i][j])){
                    return false;
                }
            }
        }
        return true;
    }
    public static Stack doBFS(int[][] initial_state){
        List<Node> nodes;
        int counter=0;
        Node in = new Node(null, null,initial_state,0,1,goal);
        //showState(in);
        Deque<Node> queue = new LinkedList<>();
        queue.addLast(in);
        while(queue.size()!=0)
        {
            Node current= queue.removeFirst();
            System.out.println("This is current node "+current.getDepth());
            showState(current);
           //System.out.println("Node: "+counter+ " and depth: "+current.getDepth());
            if(goalState(current,goal))
            {
                Stack s= getMoves(current);
//                System.out.println("Depth: "+String.valueOf(current.getDepth()));
//                System.out.println("Number of nodes: "+String.valueOf(counter));
                return s;
            }else{
                nodes=expandNode(current,false);
                System.out.println("These are expanded nodes: ");
                for(Node n:nodes) {
                    showState(n);
                    System.out.println("Depth "+n.getDepth());
                    queue.addLast(n);
                }
            }
            counter++;
        }
        return null;
    }

    public static Stack doDFS(int[][] initial_state){
        List<Node> nodes;
        int counter=0;
        Node in = new Node(null, null,initial_state,0,1,goal);
        Stack stack=new Stack();
        stack.push(in);
        while(!stack.empty())
        {
            Node current= (Node) stack.pop();
            System.out.println("Current node: "+current.getDepth());
            showState(current);
           // System.out.println("Number of times: "+counter+ " and depth: "+current.getDepth());
            counter++;
            if(goalState(current,goal))
            {
                Stack s= getMoves(current);
                System.out.println("Depth: "+String.valueOf(current.getDepth()));
                System.out.println("Number of nodes: "+String.valueOf(counter));
                return s;
            }else{
                nodes=expandNode(current,true);
                System.out.println("These are expanded nodes: ");
                for(Node n:nodes) {
                    showState(n);
                    System.out.println("Depth "+n.getDepth());
                    stack.push(n);
                }
            }
        }
        return null;
    }

    public static Stack doIDS(int[][] initial_state, int[][] goal_state, int limit)
    {
        Stack<String> sol;
        System.out.print("LIMIT IS:"+limit+" ");
        sol= doDLS(initial_state,limit);
        if(sol==null)
        {
            return doIDS(initial_state,goal_state,limit+1);
        }
        else{
            return sol;
        }

    }

    public static Stack doDLS(int[][] initial_state,  int limit){
        List<Node> nodes;

        Node in = new Node(null, null,initial_state,0,1,goal);
        Stack stack=new Stack();
        stack.push(in);
        while(!stack.empty())
        {
            Node current= (Node) stack.pop();
            counter++;
            System.out.println("Current node: "+current.getDepth());
            showState(current);
            //System.out.println("Number of times: "+counter+ " and depth: "+limit);

            if(goalState(current,goal))
            {
                Stack s= getMoves(current);
//                System.out.println("Depth: "+String.valueOf(current.getDepth()));
//                System.out.println("Number of nodes: "+String.valueOf(counter));
                return s;
            }else{
                if(current.getDepth()<limit){
                nodes=expandNode(current,false);
                System.out.println("These are expanded nodes: ");
                Collections.reverse(nodes);
                for(Node n:nodes) {
                    showState(n);
                    System.out.println("Depth "+n.getDepth());
                    stack.push(n);
                }
                }
            }

        }
        return null;
    }

    public static Stack doAStar(int[][] initial_state, int[][] goal_state)
    {
        List<Node> nodes;
        int counter=0;
        Node in = new Node(null, null,initial_state,0,1,goal_state);
        Comparator<Node> comparator = new ManhattanComparator();
        PriorityQueue<Node> queue = new PriorityQueue<>(10, comparator);
        queue.add(in);
        while(queue.size()!=0)
        {
            Node current= queue.poll();
            counter++;
            System.out.println("Current node: "+current.getDepth());
            showState(current);
            if(goalState(current,goal_state))
            {
                Stack s= getMoves(current);
//                System.out.println("Depth: "+String.valueOf(current.getDepth()));
//                System.out.println("Number of nodes: "+String.valueOf(counter));
                return s;
            }else{
                nodes=expandNode(current,false);
                System.out.println("These are expanded nodes: ");
                for(Node n:nodes) {
                    showState(n);
                    System.out.println("Depth "+n.getDepth());
                    queue.add(n);
                }
            }

        }
        return null;
    }



}