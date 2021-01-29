你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 
表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) 
（注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。

请你返回从左上角走到右下角的最小 体力消耗值 。

示例 1：
输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
输出：2
解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。

示例 2：
输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
输出：1
解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。

示例 3：
输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
输出：0
解释：上图所示路径不需要消耗任何体力。

提示：
    rows == heights.length
    columns == heights[i].length
    1 <= rows, columns <= 100
    1 <= heights[i][j] <= 106

// 找最小的体力消耗值 => 找到一个最短的长度（两点之间差的绝对值），使得图能联通

class Solution {
    public int minimumEffortPath(int[][] heights) {
        int ans = 0;
        int left = 0, right = 999999, mid = 0;
        int rows = heights.length, cols = heights[0].length;
        int[][] dir = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while(left <= right){
            mid = (left + right) >> 1;
            Queue<int[]> q = new LinkedList<>();
            boolean[][] vis = new boolean[rows][cols];
            vis[0][0] = true;
            q.offer(new int[]{0, 0});
            while(!q.isEmpty()){
                int len = q.size();
                for(int i = 0; i < len; ++i){
                    int[] temp = q.poll();
                    for(int j = 0; j < 4; ++j){
                        int tempr = temp[0] + dir[j][0];
                        int tempc = temp[1] + dir[j][1];
                        if(tempr >= 0 && tempr < rows && tempc >= 0 && tempc < cols && !vis[tempr][tempc] &&
                            Math.abs(heights[temp[0]][temp[1]] - heights[tempr][tempc]) <= mid){
                                q.offer(new int[]{tempr, tempc});
                                vis[tempr][tempc] = true;
                            }
                    }
                }
            }
            if(vis[rows - 1][cols - 1]){
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
