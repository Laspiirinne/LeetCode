给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。

示例 1：
输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1

示例 2：
输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3

// DFS
class Solution {
    private int rows;
    private int cols;
    private int ans;
    public int numIslands(char[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.ans = 0;
        for(int i = 0; i < this.rows; ++i){
            for(int j = 0; j < this.cols; ++j){
                if(grid[i][j] != '0'){
                    DFS(grid, i, j);
                    ++this.ans;
                }
            }
        }
        return this.ans;
    }
    public void DFS(char[][] grid, int r, int c){
        if(r >= 0 && r < this.rows && c >=0 && c < this.cols && grid[r][c] != '0'){
            grid[r][c] = '0';
            DFS(grid, r - 1, c);
            DFS(grid, r, c + 1);
            DFS(grid, r + 1, c);
            DFS(grid, r, c - 1);
        }
    }
}

// BFS
class Solution {
    private int rows;
    private int cols;
    private int ans;
    public int numIslands(char[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.ans = 0;
        for(int i = 0; i < this.rows; ++i){
            for(int j = 0; j < this.cols; ++j){
                if(grid[i][j] == '1'){
                    Queue<Integer> q = new LinkedList<>();
                    ++this.ans;
                    grid[i][j] = '0';
                    q.offer(i * this.cols + j);
                    int len = 0;
                    int temp = 0;
                    while(!q.isEmpty()){
                        len = q.size();
                        for(int n = 0; n < len; ++n){
                            temp = q.poll();
                            int r = temp / this.cols;
                            int c = temp % this.cols;
                            if(r - 1 >= 0 && grid[r - 1][c] == '1'){
                                q.offer((r - 1) * this.cols + c);
                                grid[r - 1][c] = '0'; // 要及时标记，否则这个点可能会被重复加入队列中，下同
                            }                               
                            if(c + 1 < this.cols && grid[r][c + 1] == '1'){
                                q.offer(r * this.cols + c + 1);
                                grid[r][c + 1] = '0';
                            }                                
                            if(r + 1 < this.rows && grid[r + 1][c] == '1'){
                                q.offer((r + 1) * this.cols + c);
                                grid[r + 1][c] = '0';
                            }                              
                            if(c - 1 >= 0 && grid[r][c - 1] == '1'){
                                q.offer(r * this.cols + c - 1);
                                grid[r][c - 1] = '0';
                            }                               
                        }
                    }
                }
            }
        }
        return this.ans;
    }
}

