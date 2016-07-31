Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

///单纯递归解法，类似树的求path题，其实就是在原有的集合中对每集合中的每个元素都加入新元素得到子集，
然后放入原有集合中（原来的集合中的元素不用删除，因为他们也是合法子集）。
而结束条件就是如果没有元素就返回空集（注意空集不是null，而是没有元素的数组）就可以了。时间和空间都是取决于结果的数量，也就是O(2^n)


