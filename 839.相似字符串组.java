如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。形式上，
对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？

示例 1：
输入：strs = ["tars","rats","arts","star"]
输出：2

示例 2：
输入：strs = ["omv","ovm"]
输出：1

提示：
    1 <= strs.length <= 100
    1 <= strs[i].length <= 1000
    sum(strs[i].length) <= 2 * 104
    strs[i] 只包含小写字母。
    strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。

// 并查集
class Solution {
    int[] parent;
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int len = strs[0].length();
        int ans = n;
        if(n == 1){
            return 1;
        }
        this.parent = new int[n];
        for(int i = 0; i < n; ++i){
            this.parent[i] = i;
        }
        for(int i = 0; i < n; ++i){
            for(int j = i + 1; j < n; ++j){
                int fi = find(i);
                int fj = find(j);
                if(fi == fj){
                    continue;
                }
                if(judge(strs[i], strs[j], len)){
                    this.parent[fi]= fj;
                    --ans;
                }
            }
        }
        return ans;
    }
    public int find(int x){
        int idx = x;
        while(idx != this.parent[idx]){
            this.parent[idx] = this.parent[this.parent[idx]];
            idx = this.parent[idx];
        }
        return idx;
    }
    public boolean judge(String i, String j, int len){
        int cnt = 0;
        for(int pos = 0; pos < len; ++pos){
            if(i.charAt(pos) != j.charAt(pos)){
                ++cnt;
                if(cnt > 2){
                    return false;
                }
            }
        }
        return true;
    }
}
