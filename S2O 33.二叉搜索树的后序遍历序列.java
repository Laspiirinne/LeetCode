输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
参考以下这颗二叉搜索树：

     5
    / \
   2   6
  / \
 1   3

示例 1：
输入: [1,6,3,2,5]
输出: false

示例 2：
输入: [1,3,2,6,5]
输出: true

提示：
    数组长度 <= 1000

/* 如果这题说的是判断该数组是不是某二叉搜索树的中序遍历结果，只需要判断数组是否有序就行了

二叉搜索树的特点是左子树的值<根节点<右子树的值。而后续遍历的顺序是：
左子节点→右子节点→根节点；
[3，5，4，10，12，9]
后续遍历的最后一个数字一定是根节点，所以数组中最后一个数字9就是根节点，我们从前往后找到第一个比9大的数字10，
那么10后面的[10，12]（除了9）都是9的右子节点，10前面的[3，5，4]都是9的左子节点，后面的需要判断一下，如果有小于9的，
说明不是二叉搜索树，直接返回false。然后再以递归的方式判断左右子树。

再来看一个，后续遍历是[3，5，13，10，12，9]
我们来根据数组拆分，第一个比9大的后面都是9的右子节点[13，10，12]。然后再拆分这个数组，12是根节点，第一个比12大的后面
都是12的右子节点[13，10]，但看到10是比12小的，他不可能是12的右子节点，
能确定这棵树不是二叉搜索树。
/*
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0 , postorder.length - 1);
    }
    public boolean verify(int[] postorder, int l, int r){
        if(l >= r)
            return true;
        int mid = l;
        while(mid < r && postorder[mid] < postorder[r])
            ++mid;
        for(int i = mid + 1; i < r; ++i){
            if(postorder[i] < postorder[r])
                return false;
        }
        return verify(postorder, l, mid - 1) && verify(postorder, mid, r - 1);
    }
}
