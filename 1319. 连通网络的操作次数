在 e >= n - 1 的时候， n 个节点的网络一定可以是联通的，只不过目前的边分配的不合理；
如果此时有 k 个连通分量，那么有的连通分量中肯定有多余的边，去除这些多余的边肯定能让
网络中 k 个联通分量连通，用多余的边把联通分量相连就行了；

// DFS
class Solution {
        boolean[] vis;
        List<Integer>[] edge;
        public int makeConnected(int n, int[][] connections) {
            if(connections.length < n - 1)
                return -1;
            this.edge = new List[n];
            this.vis = new boolean[n];
            int ans = 0;
            for(int i = 0; i < n; ++i){
                this.edge[i] = new ArrayList<Integer>();
            }
            for(int[] x : connections){
                this.edge[x[0]].add(x[1]);
                this.edge[x[1]].add(x[0]);
            }
            for(int i = 0; i < n; ++i){
                if(!this.vis[i]){
                    DFS(i);
                    ++ans;
                }
            }
            return ans - 1;
        }
        public void DFS(int x){
            if(!this.vis[x]){
                this.vis[x] = true;
                for(int next : this.edge[x]){
                    DFS(next);
                }
            }
        }
    }


// 并查集
