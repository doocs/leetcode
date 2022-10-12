# [1488. Avoid Flood in The City](https://leetcode.com/problems/avoid-flood-in-the-city)

[中文文档](/solution/1400-1499/1488.Avoid%20Flood%20in%20The%20City/README.md)

## Description

<p>Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the <code>nth</code> lake, the <code>nth</code> lake becomes full of water. If it rains over a lake that is <strong>full of water</strong>, there will be a <strong>flood</strong>. Your goal is to avoid floods in any lake.</p>

<p>Given an integer array <code>rains</code> where:</p>

<ul>
	<li><code>rains[i] &gt; 0</code> means there will be rains over the <code>rains[i]</code> lake.</li>
	<li><code>rains[i] == 0</code> means there are no rains this day and you can choose <strong>one lake</strong> this day and <strong>dry it</strong>.</li>
</ul>

<p>Return <em>an array <code>ans</code></em> where:</p>

<ul>
	<li><code>ans.length == rains.length</code></li>
	<li><code>ans[i] == -1</code> if <code>rains[i] &gt; 0</code>.</li>
	<li><code>ans[i]</code> is the lake you choose to dry in the <code>ith</code> day if <code>rains[i] == 0</code>.</li>
</ul>

<p>If there are multiple valid answers return <strong>any</strong> of them. If it is impossible to avoid flood return <strong>an empty array</strong>.</p>

<p>Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> rains = [1,2,3,4]
<strong>Output:</strong> [-1,-1,-1,-1]
<strong>Explanation:</strong> After the first day full lakes are [1]
After the second day full lakes are [1,2]
After the third day full lakes are [1,2,3]
After the fourth day full lakes are [1,2,3,4]
There&#39;s no day to dry any lake and there is no flood in any lake.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rains = [1,2,0,0,2,1]
<strong>Output:</strong> [-1,-1,2,1,-1,-1]
<strong>Explanation:</strong> After the first day full lakes are [1]
After the second day full lakes are [1,2]
After the third day, we dry lake 2. Full lakes are [1]
After the fourth day, we dry lake 1. There is no full lakes.
After the fifth day, full lakes are [2].
After the sixth day, full lakes are [1,2].
It is easy that this scenario is flood-free. [-1,-1,1,2,-1,-1] is another acceptable scenario.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> rains = [1,2,0,1,2]
<strong>Output:</strong> []
<strong>Explanation:</strong> After the second day, full lakes are  [1,2]. We have to dry one lake in the third day.
After that, it will rain over lakes [1,2]. It&#39;s easy to prove that no matter which lake you choose to dry in the 3rd day, the other one will flood.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= rains.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= rains[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def avoidFlood(self, rains: List[int]) -> List[int]:
        n = len(rains)
        ans = [-1] * n
        sunny = []
        rainy = {}
        for i, v in enumerate(rains):
            if v:
                if v in rainy:
                    idx = bisect_right(sunny, rainy[v])
                    if idx == len(sunny):
                        return []
                    ans[sunny.pop(idx)] = v
                rainy[v] = i
            else:
                sunny.append(i)
                ans[i] = 1
        return ans
```

### **Java**

```java
class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        TreeSet<Integer> sunny = new TreeSet<>();
        Map<Integer, Integer> rainy = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int v = rains[i];
            if (v > 0) {
                if (rainy.containsKey(v)) {
                    Integer t = sunny.higher(rainy.get(v));
                    if (t == null) {
                        return new int[0];
                    }
                    ans[t] = v;
                    sunny.remove(t);
                }
                rainy.put(v, i);
            } else {
                sunny.add(i);
                ans[i] = 1;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> avoidFlood(vector<int>& rains) {
        int n = rains.size();
        vector<int> ans(n, -1);
        set<int> sunny;
        unordered_map<int, int> rainy;
        for (int i = 0; i < n; ++i) {
            int v = rains[i];
            if (v) {
                if (rainy.count(v)) {
                    auto it = sunny.upper_bound(rainy[v]);
                    if (it == sunny.end()) {
                        return {};
                    }
                    ans[*it] = v;
                    sunny.erase(it);
                }
                rainy[v] = i;
            } else {
                sunny.insert(i);
                ans[i] = 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func avoidFlood(rains []int) []int {
	n := len(rains)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	sunny := []int{}
	rainy := map[int]int{}
	for i, v := range rains {
		if v > 0 {
			if j, ok := rainy[v]; ok {
				idx := sort.Search(len(sunny), func(i int) bool { return sunny[i] > j })
				if idx == len(sunny) {
					return []int{}
				}
				ans[sunny[idx]] = v
				sunny = append(sunny[:idx], sunny[idx+1:]...)
			}
			rainy[v] = i
		} else {
			sunny = append(sunny, i)
			ans[i] = 1
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
