import java.util.Comparator;

public class ManhattanComparator implements Comparator<Node>
{
    @Override
    public int compare(Node x, Node y)
    {
        if (x.getSum()+x.getDepth() < y.getSum()+y.getDepth())
        {
            return -1;
        }
        if (x.getSum()+x.getDepth() > y.getSum()+y.getDepth())
        {
            return 1;
        }
        return 0;
    }

}