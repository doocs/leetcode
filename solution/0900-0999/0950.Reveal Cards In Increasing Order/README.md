# [950. 按递增顺序显示卡牌](https://leetcode.cn/problems/reveal-cards-in-increasing-order)

[English Version](/solution/0900-0999/0950.Reveal%20Cards%20In%20Increasing%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>牌组中的每张卡牌都对应有一个唯一的整数。你可以按你想要的顺序对这套卡片进行排序。</p>

<p>最初，这些卡牌在牌组里是正面朝下的（即，未显示状态）。</p>

<p>现在，重复执行以下步骤，直到显示所有卡牌为止：</p>

<ol>
	<li>从牌组顶部抽一张牌，显示它，然后将其从牌组中移出。</li>
	<li>如果牌组中仍有牌，则将下一张处于牌组顶部的牌放在牌组的底部。</li>
	<li>如果仍有未显示的牌，那么返回步骤 1。否则，停止行动。</li>
</ol>

<p>返回能以<strong>递增顺序</strong>显示卡牌的牌组顺序。</p>

<p>答案中的第一张牌被认为处于牌堆顶部。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>[17,13,11,2,3,5,7]
<strong>输出：</strong>[2,13,3,11,5,17,7]
<strong>解释：
</strong>我们得到的牌组顺序为 [17,13,11,2,3,5,7]（这个顺序不重要），然后将其重新排序。
重新排序后，牌组以 [2,13,3,11,5,17,7] 开始，其中 2 位于牌组的顶部。
我们显示 2，然后将 13 移到底部。牌组现在是 [3,11,5,17,7,13]。
我们显示 3，并将 11 移到底部。牌组现在是 [5,17,7,13,11]。
我们显示 5，然后将 17 移到底部。牌组现在是 [7,13,11,17]。
我们显示 7，并将 13 移到底部。牌组现在是 [11,17,13]。
我们显示 11，然后将 17 移到底部。牌组现在是 [13,17]。
我们展示 13，然后将 17 移到底部。牌组现在是 [17]。
我们显示 17。
由于所有卡片都是按递增顺序排列显示的，所以答案是正确的。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 1000</code></li>
	<li><code>1 &lt;= A[i] &lt;= 10^6</code></li>
	<li>对于所有的&nbsp;<code>i != j</code>，<code>A[i] != A[j]</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：队列模拟**

根据题目描述，我们知道，数组 `deck` 逆序排列后的序列就是最终的结果。我们可以从最终结果入手，逆向推导出卡片顺序。

遍历逆序排列后的数组 `deck`，先判断队列是否为空，若不为空，则将队尾元素移动到队首，然后将当前元素入队（题目中的逆过程）。若为空，则直接将当前元素入队。

最后，将队列中的元素依次出队，即可得到最终的结果。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `deck` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def deckRevealedIncreasing(self, deck: List[int]) -> List[int]:
        q = deque()
        for v in sorted(deck, reverse=True):
            if q:
                q.appendleft(q.pop())
            q.appendleft(v)
        return list(q)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        Deque<Integer> q = new ArrayDeque<>();
        Arrays.sort(deck);
        int n = deck.length;
        for (int i = n - 1; i >= 0; --i) {
            if (!q.isEmpty()) {
                q.offerFirst(q.pollLast());
            }
            q.offerFirst(deck[i]);
        }
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = q.pollLast();
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> deckRevealedIncreasing(vector<int>& deck) {
        sort(deck.rbegin(), deck.rend());
        deque<int> q;
        for (int v : deck) {
            if (!q.empty()) {
                q.push_front(q.back());
                q.pop_back();
            }
            q.push_front(v);
        }
        return vector<int>(q.begin(), q.end());
    }
};
```

### **Go**

```go
func deckRevealedIncreasing(deck []int) []int {
	sort.Sort(sort.Reverse(sort.IntSlice(deck)))
	q := []int{}
	for _, v := range deck {
		if len(q) > 0 {
			q = append([]int{q[len(q)-1]}, q[:len(q)-1]...)
		}
		q = append([]int{v}, q...)
	}
	return q
}
```

### **...**

```

```

<!-- tabs:end -->
