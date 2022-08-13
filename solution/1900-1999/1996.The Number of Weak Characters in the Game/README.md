# [1996. 游戏中弱角色的数量](https://leetcode.cn/problems/the-number-of-weak-characters-in-the-game)

[English Version](/solution/1900-1999/1996.The%20Number%20of%20Weak%20Characters%20in%20the%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在参加一个多角色游戏，每个角色都有两个主要属性：<strong>攻击</strong> 和 <strong>防御</strong> 。给你一个二维整数数组 <code>properties</code> ，其中 <code>properties[i] = [attack<sub>i</sub>, defense<sub>i</sub>]</code> 表示游戏中第 <code>i</code> 个角色的属性。</p>

<p>如果存在一个其他角色的攻击和防御等级 <strong>都严格高于</strong> 该角色的攻击和防御等级，则认为该角色为 <strong>弱角色</strong> 。更正式地，如果认为角色 <code>i</code> <strong>弱于</strong> 存在的另一个角色 <code>j</code> ，那么 <code>attack<sub>j</sub> &gt; attack<sub>i</sub></code> 且 <code>defense<sub>j</sub> &gt; defense<sub>i</sub></code> 。</p>

<p>返回 <strong>弱角色</strong> 的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>properties = [[5,5],[6,3],[3,6]]
<strong>输出：</strong>0
<strong>解释：</strong>不存在攻击和防御都严格高于其他角色的角色。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>properties = [[2,2],[3,3]]
<strong>输出：</strong>1
<strong>解释：</strong>第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>properties = [[1,5],[10,4],[4,3]]
<strong>输出：</strong>1
<strong>解释：</strong>第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= properties.length &lt;= 10<sup>5</sup></code></li>
	<li><code>properties[i].length == 2</code></li>
	<li><code>1 &lt;= attack<sub>i</sub>, defense<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

按攻击力从大到小排序，攻击力相同则按防御力从小到大排序。

遍历，维护遍历过的角色的防御的最大值 mx。

对于当前角色 p，如果 p 的防御小于 mx，说明前面有防御比 p 高的角色，记作 q。根据上面的排序规则，q 的攻击是大于或等于 p 的攻击的，如果 q 和 p 攻击相同，仍然根据上面的排序规则，q 的防御不会超过 p，矛盾，因此 q 的攻击必然大于 p，于是 q 的攻防均高于 p，p 是一个弱角色。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfWeakCharacters(self, properties: List[List[int]]) -> int:
        properties.sort(key=lambda x: (-x[0], x[1]))
        ans = mx = 0
        for _, d in properties:
            if mx > d:
                ans += 1
            mx = max(mx, d)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> {
            return a[0] == b[0] ? a[1] - b[1] : b[0] - a[0];
        });
        int ans = 0, mx = 0;
        for (int[] p : properties) {
            if (mx > p[1]) {
                ++ans;
            }
            mx = Math.max(mx, p[1]);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function numberOfWeakCharacters(properties: number[][]): number {
    properties.sort((a, b) => (a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]));

    let ans = 0;
    let max = 0;
    for (let [, b] of properties) {
        if (b < max) {
            ans++;
        } else {
            max = b;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfWeakCharacters(vector<vector<int>>& properties) {
        sort(properties.begin(), properties.end(), [&](vector<int>& a, vector<int>& b) { return a[0] == b[0] ? a[1] < b[1] : a[0] > b[0]; });
        int ans = 0, mx = 0;
        for (auto& p : properties) {
            if (mx > p[1])
                ++ans;
            else
                mx = p[1];
        }
        return ans;
    }
};
```

### **Go**

```go
func numberOfWeakCharacters(properties [][]int) int {
	sort.Slice(properties, func(i, j int) bool {
		if properties[i][0] == properties[j][0] {
			return properties[i][1] < properties[j][1]
		}
		return properties[i][0] > properties[j][0]
	})
	ans, mx := 0, 0
	for _, p := range properties {
		if mx > p[1] {
			ans++
		} else {
			mx = p[1]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
