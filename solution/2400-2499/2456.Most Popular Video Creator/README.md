# [2456. 最流行的视频创作者](https://leetcode.cn/problems/most-popular-video-creator)

[English Version](/solution/2400-2499/2456.Most%20Popular%20Video%20Creator/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串数组 <code>creators</code> 和 <code>ids</code> ，和一个整数数组 <code>views</code> ，所有数组的长度都是 <code>n</code> 。平台上第 <code>i</code> 个视频者是&nbsp;<code>creator[i]</code> ，视频分配的 id 是 <code>ids[i]</code> ，且播放量为 <code>views[i]</code> 。</p>

<p>视频创作者的 <strong>流行度</strong> 是该创作者的 <strong>所有</strong> 视频的播放量的 <strong>总和</strong> 。请找出流行度 <strong>最高</strong> 创作者以及该创作者播放量 <strong>最大</strong> 的视频的 id 。</p>

<ul>
	<li>如果存在多个创作者流行度都最高，则需要找出所有符合条件的创作者。</li>
	<li>如果某个创作者存在多个播放量最高的视频，则只需要找出字典序最小的 <code>id</code> 。</li>
</ul>

<p>返回一个二维字符串数组<em> </em><code>answer</code><em> </em>，其中<em> </em><code>answer[i] = [creator<sub>i</sub>, id<sub>i</sub>]</code><em> </em>表示<em> </em><code>creator<sub>i</sub></code> 的流行度 <strong>最高</strong> 且其最流行的视频 id 是<em> </em><code>id<sub>i</sub></code><em> </em>，可以按任何顺序返回该结果<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>creators = ["alice","bob","alice","chris"], ids = ["one","two","three","four"], views = [5,10,5,4]
<strong>输出：</strong>[["alice","one"],["bob","two"]]
<strong>解释：</strong>
alice 的流行度是 5 + 5 = 10 。
bob 的流行度是 10 。
chris 的流行度是 4 。
alice 和 bob 是流行度最高的创作者。
bob 播放量最高的视频 id 为 "two" 。
alice 播放量最高的视频 id 是 "one" 和 "three" 。由于 "one" 的字典序比 "three" 更小，所以结果中返回的 id 是 "one" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>creators = ["alice","alice","alice"], ids = ["a","b","c"], views = [1,2,2]
<strong>输出：</strong>[["alice","b"]]
<strong>解释：</strong>
id 为 "b" 和 "c" 的视频都满足播放量最高的条件。
由于 "b" 的字典序比 "c" 更小，所以结果中返回的 id 是 "b" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == creators.length == ids.length == views.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= creators[i].length, ids[i].length &lt;= 5</code></li>
	<li><code>creators[i]</code> 和 <code>ids[i]</code> 仅由小写英文字母组成</li>
	<li><code>0 &lt;= views[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们用哈希表 `cnt` 统计每个创作者的视频播放量总和，用哈希表 `d` 和 `x` 记录每个创作者的最大播放量和对应的视频 `id`。

然后遍历哈希表 `cnt`，找到最大视频播放量总和的创作者，将其对应的视频 `id` 加入答案数组 `ans` 中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为视频数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mostPopularCreator(self, creators: List[str], ids: List[str], views: List[int]) -> List[List[str]]:
        cnt = defaultdict(int)
        d = {}
        x = {}
        for c, i, v in zip(creators, ids, views):
            cnt[c] += v
            if c not in d or d[c] < v or (d[c] == v and x[c] > i):
                d[c], x[c] = v, i
        ans = []
        pre = -1
        for a, b in cnt.items():
            if b > pre:
                ans = [[a, x[a]]]
                pre = b
            elif b == pre:
                ans.append([a, x[a]])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Integer> cnt = new HashMap<>();
        Map<String, Integer> d = new HashMap<>();
        Map<String, String> x = new HashMap<>();
        int n = ids.length;
        for (int k = 0; k < n; ++k) {
            var c = creators[k];
            var i = ids[k];
            int v = views[k];
            cnt.put(c, cnt.getOrDefault(c, 0) + v);
            if (!d.containsKey(c) || d.get(c) < v || (d.get(c) == v && x.get(c).compareTo(i) > 0)) {
                d.put(c, v);
                x.put(c, i);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        int pre = -1;
        for (var e : cnt.entrySet()) {
            String a = e.getKey();
            int b = e.getValue();
            if (b > pre) {
                ans.clear();
                ans.add(Arrays.asList(a, x.get(a)));
                pre = b;
            } else if (b == pre) {
                ans.add(Arrays.asList(a, x.get(a)));
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
    vector<vector<string>> mostPopularCreator(vector<string>& creators, vector<string>& ids, vector<int>& views) {
        unordered_map<string, long> cnt;
        unordered_map<string, int> d;
        unordered_map<string, string> x;
        int n = ids.size();
        for (int k = 0; k < n; ++k) {
            auto c = creators[k];
            auto i = ids[k];
            int v = views[k];
            cnt[c] += v;
            if (!d.count(c) || d[c] < v || (d[c] == v && x[c] > i)) {
                d[c] = v;
                x[c] = i;
            }
        }
        long pre = -1;
        vector<vector<string>> ans;
        for (auto& [a, b] : cnt) {
            if (b > pre) {
                ans.clear();
                ans.push_back({a, x[a]});
                pre = b;
            } else if (b == pre) {
                ans.push_back({a, x[a]});
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func mostPopularCreator(creators []string, ids []string, views []int) (ans [][]string) {
	cnt := map[string]int{}
	d := map[string]int{}
	x := map[string]string{}
	for k, c := range creators {
		i, v := ids[k], views[k]
		cnt[c] += v
		if t, ok := d[c]; !ok || t < v || (t == v && x[c] > i) {
			d[c] = v
			x[c] = i
		}
	}
	pre := -1
	for a, b := range cnt {
		if b > pre {
			ans = [][]string{[]string{a, x[a]}}
			pre = b
		} else if b == pre {
			ans = append(ans, []string{a, x[a]})
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
