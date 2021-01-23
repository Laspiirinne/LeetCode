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
