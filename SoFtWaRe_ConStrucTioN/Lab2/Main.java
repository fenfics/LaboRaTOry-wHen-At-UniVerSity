public class Main{
    public static void main(String[] args) {
        
    final int size = 5;
    int[][] grid = new int[size][size];
    for(int i =0;i<size;i++){
        for(int j = 0 ; j<size;j++){
            grid[i][j] = i * size + j +1;
            }
        }
        for(int i =0;i<size;i++){
            for(int j = 0 ; j<size;j++){
                grid[i][j] = i * size + j +1;
                System.out.print(grid[j][i]+"\t");
                }
                System.out.println();
            }System.out.println("\n");

            String result = "";
            int loop = 4;
            for(int i =0;i<size;i++){
                for(int j = 0 ; j<size;j++){
                    if(i%2==1)
                    result += grid[i][loop-j]+"\t";
                    else
                    result += grid[i][j]+"\t";
                    }
                    result += "\n";
                }
                System.out.print(result);
                
    }
}
