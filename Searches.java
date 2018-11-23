import java.util.*;

public class Searches {

    public static void main(String[] args){

        int i,j;
        int[][] initial=new int[4][4];
        int[][] goal=new int[4][4];
        for(i=0;i<4;i++){
            for(j=0;j<4;j++)
            {
                initial[i][j]=0;
            }
        }
        initial[1][1]=65;
        initial[2][1]=66;
        initial[3][2]=67;
        initial[3][3]=42;

        for(i=0;i<4;i++){
            for(j=0;j<4;j++)
            {
                goal[i][j]=0;
            }
        }
        goal[1][1]=65;
        goal[2][1]=66;
        goal[3][1]=67;
        goal[3][3]=42;

        System.out.print(doBFS(initial,goal));

    }
    public static List<String> getRandom(){
        List<String> move=  Arrays.asList("up","down","left","right");
        Collections.shuffle(move);
        return move;
    }
    public static List<Node> expandNode(Node parent)
    {
        List<Node> list=new ArrayList<>();
        List<String> moves= getRandom();
        for(int i=0;i<4;i++)
        {
            if(parent.calculateState(parent,moves.get(i))!=null) {
                list.add(new Node(parent, moves.get(i), parent.getDepth() + 1, 1));
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
                if (!(current.getState()[i][j]!=0&&current.getState()[i][j]!=42&current.getState()[i][j]==goal[i][j])){
                    return false;
                }
            }
        }
        return true;
    }
    public static String doBFS(int[][] initial_state, int[][] goal_state){
        String ok="Not there";
        List<Node> nodes=null;
        int counter=0;
        Node in = new Node(null, null,initial_state,0,1);
        Stack stack=new Stack();
        stack.push(in);
        while(!stack.empty())
        {
            Node current= (Node) stack.pop();
            System.out.println("Number of times: "+counter);
            if(goalState(current,goal_state))
            {
                ok="I reached the solution";
                break;
            }else{
                nodes=expandNode(current);
                for(Node n:nodes) {
                    stack.push(n);
                }
            }
            counter++;
        }
        return ok;
    }
}