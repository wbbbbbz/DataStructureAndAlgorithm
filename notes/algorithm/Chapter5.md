# 第 5 章 二分搜索树

> ## 参考[玩转数据结构 从入门到进阶-慕课网实战 第 6 章 二分搜索树](notes/datastructure/Chapter6.md)

- 注意二分查找的细节
- floor, ceil
  - 保证 floor 是 target 第一次出现的 index,或者是前一个元素的最后一个 index
  - 保证 ceil 是 target 第最后一次出现的 index,或者是后一个元素的第一个 index
  - 同样也是对index的细节处理!

- 二分搜索树一般是用来实现查找表(字典)
  - 查找,插入,删除都非常高效
  - 并且min, max, floor, ceil, rank, select都很方便
  - 遍历是O(n)

- 树形问题
  - 递归法就是天然树结构
  - 搜索问题
    - 枚举(决策树)
