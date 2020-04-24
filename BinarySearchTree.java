给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

    节点的左子树只包含小于当前节点的数。
    节点的右子树只包含大于当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。

输入:             输入:
    2                 5
   / \               / \
  1   3             1   4
输出: true              / \
                       3   6
输入:               输出: false        
                  解释: 输入为: [5,1,4,null,null,3,6]。
                  根节点的值为 5 ，但是其右子节点值为 4 。
                  
class Solution {
    public boolean f(TreeNode node,Integer low,Integer high){
        if(node==null)
            return true;
        if(low!=null&&node.val<=low)
            return false;
        if(high!=null&&node.val>=high)
            return false;
        if(node.left!=null&&node.right!=null)
            return f(node.left,low,node.val)&&f(node.right,node.val,high);
        if(node.left==null&&node.right!=null)
            return f(node.right,node.val,high);
        if(node.left!=null&&node.right==null)
            return f(node.left,low,node.val);
        return true;
    }
    public boolean isValidBST(TreeNode root) {
        return f(root,null,null);
    }
}
