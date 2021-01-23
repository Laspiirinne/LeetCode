在 e >= n - 1 的时候， n 个节点的网络一定可以是联通的，只不过目前的边分配的不合理；
如果此时有 k 个连通分量，那么有的连通分量中肯定有多余的边，去除这些多余的边肯定能让
网络中 k 个联通分量连通，用多余的边把联通分量相连就行了（如果边的数量 >= n-1，则肯
定能通过边的改动，连接所有节点。）；转化为统计连通分量的个数

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


// 并查集模板
class UnionFind {
  constructor(num) { // num 顶点个数
    this.roots = new Array(num)
    this.ranks = new Array(num) // 记录根节点在所在树中的高度
    for (let i = 0; i < num; i++) {
      this.roots[i] = -1
      this.ranks[i] = 0
    }
  }
  
  findRoot(x) { // 找出顶点x的根节点
    let x_root = x // 先从x节点开始
    while (this.roots[x_root] !== -1) { // 一直找父节点，找到尽头
      x_root = this.roots[x_root]
    }
    return x_root // 返回根节点
  }
  
  union(x, y) { // 把顶点x和顶点y所在的集合合并到一起
    let x_root = this.findRoot(x)
    let y_root = this.findRoot(y)
    if (x_root === y_root) return  // 已经同处于一个集合了
    let x_rank = this.ranks[x_root]
    let y_rank = this.ranks[y_root]
    if (x_rank < y_rank) {    // 谁高度大，谁就作为根节点
      this.roots[x_root] = y_root 
    } else if (y_rank < x_rank) {
      this.roots[y_root] = x_root
    } else {                  // 一样高，谁作为根节点都行
      this.roots[y_root] = x_root
      this.ranks[x_root]++ // 作为根节点的，高度会+1
    }
  }
}

// 并查集写法
class Solution {
    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if(len < n - 1)
            return -1;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < len; ++i){
            uf.union(connections[i][0], connections[i][1]);
        }
        return uf.ans - 1;
    }
}

class UnionFind{
    int[] parent;
    int[] rank;
    int ans;
    public UnionFind(int n){
        this.parent = new int[n];
        this.rank = new int[n];
        this.ans = n;
        for(int i = 0; i < n; ++i){
            this.parent[i] = i;
        }
    }
    public void union(int x, int y){
        if(this.find(x) == this.find(y))
            return;
        int xpar = this.find(x);
        int ypar = this.find(y);
        int xrank = this.rank[xpar];
        int yrank = this.rank[ypar];
        if(xrank < yrank){
            this.parent[xpar] = ypar;
        } else if(xrank > yrank) {
            this.parent[ypar] = xpar;
        } else {
            this.parent[xpar] = ypar;
            ++this.rank[ypar];
        }
        --this.ans;
    }
    public int find(int x){
        int idx = x;
        while(this.parent[idx] != idx){
            idx = this.parent[idx];
        }
        return idx;
    }
}
