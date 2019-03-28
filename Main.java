import java.util.*;
import java.lang.*;

class Node
{
  int depth;
  int state[][] = new int[5][5];
  int queens[] = new int[5];
  Node(int depth, int[][] state,int[] queens)
  {
    this.depth = depth;
    this.state = state;
    this.queens = queens;
  }
}
class Main
{
  static int board[][] = new int[5][5];
  static int pos[] = new int[5];
  static Vector <Node> ss = new Vector <>();
  static Deque <Node> queue = new ArrayDeque <>();
  public static void main(String args[])
  {
    Node root = new Node(1,new int[5][5],new int[5]);
    queue.addLast(root);
    ss.add(root);
    createStateSpace();
    System.out.println("\nState space tree generated with number of nodes = "+ss.size());
    ids();
  }

  public static void createStateSpace()
  {
    int depth,x,y,j,k,queen=1;
    int childstate[][];
    int childpos[];
    while(!queue.isEmpty())
    {
      Node parent = queue.remove();
      depth = parent.depth + 1;
      queen = depth-1; 
      for(y=1;y<=4;y++)
      {
        childstate = new int[5][5];
        childpos = new int[5];
        for(j=1;j<=4;j++)
        {
          childpos[j] = parent.queens[j];
          for(k=1;k<=4;k++)
            childstate[j][k] = parent.state[j][k] ;
        } 
        if(canPlace(queen,y,parent.queens)) //can place xth queen on x row and y col
        {
          childstate[queen][y] = queen;
          childpos[queen] = y;
          Node child = new Node(depth,childstate,childpos);
          queue.add(child);
          ss.add(child);
        }
      }
    }
  }

  public static void ids()
  {
    int limit = 1,i,j,k,maxlimit;
    maxlimit = ss.lastElement().depth;
    Stack <Node> stack = new Stack <>();
    while(limit<=maxlimit)
    {
      System.out.println("\nDepth Limit = "+limit);
      //dfs starts
      Node root = ss.firstElement();
      stack.push(root);
      while(!stack.empty())
      {
        Node node = stack.pop();
        if(node.depth<=limit)
        {
          System.out.println("Depth = "+node.depth);
          print(node.state);
          System.out.println("\n");
          if(isGoal(node))
          {
            System.out.println("Goal Node Found\n");
            System.exit(-1);
          }
          else
          {
            Vector <Node> children = getChildren(node);
            for (Node child:children)
              stack.push(child);
          }
        }
      }
      System.out.printf("Goal Not Found\n");
      limit++;
    }
  }

  public static boolean canPlace(int queen, int row,int pos[])
  {
    int j;
    for (j=1; j<queen; j++)
    {
      if((pos[j] == row) || (Math.abs(pos[j]-row) == Math.abs(j-queen)))
      return false;
    }
    return true;
  }
  public static void print(int state[][])
  {
    for (int i=1;i<=4;i++)
    {
      for(int j=1;j<=4;j++)
        System.out.print(state[i][j]+"\t");
      System.out.print("\n");

    }
  }
  public static boolean isGoal(Node node)
  {
    int goal1[] = {0,2,4,1,3};
    int goal2[] = {0,3,1,4,2};
    if (Arrays.equals(node.queens, goal1) || Arrays.equals(node.queens, goal2))
      return true;
    return false;
  }
  public static Vector getChildren(Node parent)
  {
    Vector <Node> children = new Vector<>();
    int pdepth = parent.depth;
    int ppos[] = Arrays.copyOfRange(parent.queens,1,pdepth);
    for (Node node:ss)
    {
      if(node.depth==pdepth+1)
      {
         int cpos[] = Arrays.copyOfRange(node.queens,1,pdepth);
         if(Arrays.equals(ppos,cpos))
          children.add(node);
      }
    }
    return children;
  }
}