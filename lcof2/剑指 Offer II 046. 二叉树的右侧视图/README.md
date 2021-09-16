# [剑指 Offer II 046. 二叉树的右侧视图](https://leetcode-cn.com/problems/WNC0Lk)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20046.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E5%8F%B3%E4%BE%A7%E8%A7%86%E5%9B%BE/images/tree.jpg" style="width: 270px; " /></p>

<pre>
<strong>输入:</strong>&nbsp;[1,2,3,null,5,null,4]
<strong>输出:</strong>&nbsp;[1,3,4]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;[1,null,3]
<strong>输出:</strong>&nbsp;[1,3]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong>&nbsp;[]
<strong>输出:</strong>&nbsp;[]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[0,100]</code></li>
	<li><meta charset="UTF-8" /><code>-100&nbsp;&lt;= Node.val &lt;= 100</code>&nbsp;</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 199&nbsp;题相同：<a href="https://leetcode-cn.com/problems/binary-tree-right-side-view/">https://leetcode-cn.com/problems/binary-tree-right-side-view/</a></p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **C++**

```cpp
class Solution
{
public:
    vector<int> rightSideView ( TreeNode* root )
    {
        vector<int> res;

        if ( !root )
        {
            return res;
        }

        queue<TreeNode* > que;
        que.push ( root );

        while ( !que.empty() )
        {
            int size = que.size();

            for ( int i = 0; i < size; i++ )
            {
                TreeNode* ptr = que.front();
                que.pop();

                if ( i == size - 1 )
                {
                    res.push_back ( ptr->val );
                }

                if ( ptr->left )
                {
                    que.push ( ptr->left );
                }

                if ( ptr-> right )
                {
                    que.push ( ptr->right );
                }
            }
        }

        return res;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
