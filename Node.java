import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Node {
    int[][] state;
    Node parent;
    String move;
    int depth;
    int cost;
    int sum;


    public Node(Node parent, String move, int depth, int cost,int[][] goal)
    {
        this.parent=parent;
        this.move=move;
        this.state=this.calculateState(parent,move);
        this.depth=depth;
        this.cost=cost;
        this.sum=getManhattanSum(this.state,goal);
    }

    public Node(Node parent, String move, int[][] state, int depth, int cost,int[][] goal)
    {
        this.parent=parent;
        this.move=move;
        this.state=state;
        this.depth=depth;
        this.cost=cost;
        this.sum=getManhattanSum(this.state,goal);
    }

    public int getManhattanSum(int[][] current, int[][] goal)
    {
        int i,j,k,l,sum=0;
        for(i=0; i<current.length;i++) {
            for (j = 0; j < current.length; j++) {
                if (current[i][j] != 0 && current[i][j] != 42) {
                    for (k = 0; k < goal.length; k++) {
                        for (l = 0; l < goal.length; l++) {
                            if (current[i][j] == goal[k][l]) {
                                sum += Math.abs(i - k) + Math.abs(j - l);
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }
    public int getSum()
    {
        return this.sum;
    }
    public Node getParent()
    {
        return this.parent;
    }
    public int[][] calculateState(Node parent,String move)
    {
        int[][] new_state=new int[4][4];
        int i,j,aux, pos_i, pos_j;

        if(parent!=null) {
            for (pos_i = 0; pos_i < parent.getState().length; pos_i++)
                for (pos_j = 0; pos_j < parent.getState().length; pos_j++) {
                    new_state[pos_i][pos_j]=parent.getState()[pos_i][pos_j];
                }
        }else{
            new_state=this.getState();
        }
        Pair p =getActorPosition(new_state);
        i=p.getI();
        j=p.getJ();
        if(move.equals("up"))
        {
            if((j-1)>=0)
            {
                aux=new_state[i][j];
                new_state[i][j]=new_state[i][j-1];
                new_state[i][j-1]=aux;

            }
            else
            {
                new_state=null;
            }
        } else{
            if(move.equals("down"))
            {
                if((j+1)<new_state.length)
                {
                    aux=new_state[i][j];
                    new_state[i][j]=new_state[i][j+1];
                    new_state[i][j+1]=aux;
                }
                else
                {
                    new_state=null;
                }
            } else{
                if(move.equals("left"))
                {
                    if((i-1)>=0)
                    {
                        aux=new_state[i][j];
                        new_state[i][j]=new_state[i-1][j];
                        new_state[i-1][j]=aux;
                    }
                    else
                    {
                        new_state=null;
                    }

                }else{
                    if(move.equals("right")) {
                        if ((i + 1) < new_state.length) {
                            aux = new_state[i][j];
                            new_state[i][j] = new_state[i + 1][j];
                            new_state[i + 1][j] = aux;
                        } else {
                            new_state = null;
                        }
                    }

                }
            }
        }
        return new_state;
    }
    public int[][] getState()
    {
        return this.state;
    }
    public int getDepth()
    {
        return this.depth;
    }
    public String getMove(){
        return this.move;
    }

    public Pair getActorPosition(int[][] state)
    {
        Pair p=null;
        int i,j;
        for(i=0;i<state.length;i++) {
            for (j = 0; j < state.length; j++) {
                if(state[i][j]==42) {
                    p = new Pair(i,j);
                    break;
                }
            }
        }
    return p;
    }
}
