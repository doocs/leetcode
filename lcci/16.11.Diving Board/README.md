# [面试题 16.11. 跳水板](https://leetcode.cn/problems/diving-board-lcci)

[English Version](/lcci/16.11.Diving%20Board/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为<code>shorter</code>，长度较长的木板长度为<code>longer</code>。你必须正好使用<code>k</code>块木板。编写一个方法，生成跳水板所有可能的长度。</p>
<p>返回的长度需要从小到大排列。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
shorter = 1
longer = 2
k = 3
<strong>输出：</strong> {3,4,5,6}
</pre>
<p><strong>提示：</strong></p>
<ul>
<li>0 < shorter <= longer</li>
<li>0 <= k <= 100000</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：分类讨论**

如果 $k=0$，则不存在任何一种方案，我们可以直接返回空列表。

如果 $shorter=longer$，则我们只能使用长度为 $longer \times k$ 的木板，因此我们直接返回长度为 $longer \times k$ 的列表。

否则，我们可以使用长度为 $shorter \times (k-i) + longer \times i$ 的木板，其中 $0 \leq i \leq k$。我们在 $[0, k]$ 的范围内枚举 $i$，并计算对应的长度即可。对于不同的 $i$，我们不会得到相同的长度，这是因为，假如有 $0 \leq i \lt j \leq k$，那么两者长度差为 $shorter \times (k-i) + longer \times i - shorter \times (k-j) - longer \times j$，整理得到长度差 $(i - j) \times (longer - shorter) \lt 0$。因此，对于不同的 $i$，我们会得到不同的长度。

时间复杂度 $O(k)$，其中 $k$ 为木板数量。忽略答案的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def divingBoard(self, shorter: int, longer: int, k: int) -> List[int]:
        if k == 0:
            return []
        if longer == shorter:
            return [longer * k]
        ans = []
        for i in range(k + 1):
            ans.append(longer * i + shorter * (k - i))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (longer == shorter) {
            return new int[] {longer * k};
        }
        int[] ans = new int[k + 1];
        for (int i = 0; i < k + 1; ++i) {
            ans[i] = longer * i + shorter * (k - i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> divingBoard(int shorter, int longer, int k) {
        if (k == 0) return {};
        if (longer == shorter) return {longer * k};
        vector<int> ans;
        for (int i = 0; i < k + 1; ++i)
            ans.push_back(longer * i + shorter * (k - i));
        return ans;
    }
};
```

### **Go**

```go
func divingBoard(shorter int, longer int, k int) []int {
	if k == 0 {
		return []int{}
	}
	if longer == shorter {
		return []int{longer * k}
	}
	var ans []int
	for i := 0; i < k+1; i++ {
		ans = append(ans, longer*i+shorter*(k-i))
	}
	return ans
}
```

### **TypeScript**

```ts
function divingBoard(shorter: number, longer: number, k: number): number[] {
    if (k === 0) {
        return [];
    }
    if (longer === shorter) {
        return [longer * k];
    }
    const ans: number[] = [k + 1];
    for (let i = 0; i <= k; ++i) {
        ans[i] = longer * i + shorter * (k - i);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
