# [1152. 用户网站访问行为分析 🔒](https://leetcode.cn/problems/analyze-user-website-visit-pattern)

[English Version](/solution/1100-1199/1152.Analyze%20User%20Website%20Visit%20Pattern/README_EN.md)

<!-- tags:数组,哈希表,排序 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串数组&nbsp;<code>username</code>&nbsp;和&nbsp;<code>website</code>&nbsp;和一个整数数组&nbsp;<code>timestamp</code>&nbsp;。给定的数组长度相同，其中元组&nbsp;<code>[username[i], website[i], timestamp[i]]</code>&nbsp;表示用户&nbsp;<code>username[i]</code>&nbsp;在时间&nbsp;<code>timestamp[i]</code>&nbsp;访问了网站&nbsp;<code>website[i]</code>&nbsp;。</p>

<p><strong>访问模式</strong> 是包含三个网站的列表(不一定是完全不同的)。</p>

<ul>
	<li>例如，<code>["home"， "away"， "love"]</code>， <code>["leetcode"， "love"， "leetcode"]</code>，和 <code>["luffy"， "luffy"， "luffy"]</code> 都是模式。</li>
</ul>

<p>一种&nbsp;<strong>访问</strong><strong>模式</strong> 的 <strong>得分</strong> 是访问该模式中所有网站的用户数量，这些网站在该模式中出现的顺序相同。</p>

<ul>
	<li>例如，如果模式是 <code>[“home”，“away”，“love”] </code>，那么分数就是用户数量 <code>x</code> , <code>x</code> 访问了 “<code>home”</code> ，然后访问了 <code>“away”</code> ，然后访问了 <code>“love” </code>。</li>
	<li>同样，如果模式是 <code>["leetcode"， "love"， "leetcode"]</code> ，那么分数就是用户数量&nbsp;<code>x</code>&nbsp;，使得 <code>x</code> 访问了<code>"leetcode"</code>，然后访问了 <code>"love"</code> ，之后又访问了 <code>"leetcode"</code> 。</li>
	<li>另外，如果模式是 <code>[“luffy”，“luffy”，“luffy”]</code>&nbsp;，那么分数就是用户数量 <code>x</code> ，这样 <code>x</code> 就可以在不同的时间戳上访问 <code>“luffy”</code> 三次。</li>
</ul>

<p>返回<em> <strong>得分</strong> 最大的 <strong>访问</strong><strong>模式</strong></em> 。如果有多个访问模式具有相同的最大分数，则返回字典序最小的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
<strong>输出：</strong>["home","about","career"]
<strong>解释：</strong>本例中的元组是:
["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],["james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary","about",9], and ["mary","career",10].
模式("home", "about", "career") has score 2 (joe and mary).
模式("home", "cart", "maps") 的得分为 1 (james).
模式 ("home", "cart", "home") 的得分为 1 (james).
模式 ("home", "maps", "home") 的得分为 1 (james).
模式 ("cart", "maps", "home") 的得分为 1 (james).
模式 ("home", "home", "home") 的得分为 0(没有用户访问过home 3次)。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], website = ["a","b","a","a","b","c"]
<strong>输出:</strong> ["a","b","a"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= username.length &lt;= 50</code></li>
	<li><code>1 &lt;= username[i].length &lt;= 10</code></li>
	<li><code>timestamp.length == username.length</code></li>
	<li><code>1 &lt;= timestamp[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>website.length == username.length</code></li>
	<li><code>1 &lt;= website[i].length &lt;= 10</code></li>
	<li><code>username[i]</code> 和&nbsp;<code>website[i]</code>&nbsp;都只含小写字符</li>
	<li>它保证至少有一个用户访问了至少三个网站</li>
	<li>所有元组&nbsp;<code>[username[i]， timestamp[i]， website[i]</code>&nbsp;均<strong>&nbsp;不重复</strong></li>
</ul>

## 解法

### 方法一：哈希表 + 排序

我们先用哈希表 $d$ 记录每个用户访问的网站，然后遍历 $d$，对于每个用户，我们枚举其访问的所有三元组，统计去重三元组的出现次数，最后遍历所有三元组，返回出现次数最多的、字典序最小的三元组。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^3)$。其中 $n$ 是 `username` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def mostVisitedPattern(
        self, username: List[str], timestamp: List[int], website: List[str]
    ) -> List[str]:
        d = defaultdict(list)
        for user, _, site in sorted(
            zip(username, timestamp, website), key=lambda x: x[1]
        ):
            d[user].append(site)

        cnt = Counter()
        for sites in d.values():
            m = len(sites)
            s = set()
            if m > 2:
                for i in range(m - 2):
                    for j in range(i + 1, m - 1):
                        for k in range(j + 1, m):
                            s.add((sites[i], sites[j], sites[k]))
            for t in s:
                cnt[t] += 1
        return sorted(cnt.items(), key=lambda x: (-x[1], x[0]))[0][0]
```

```java
class Solution {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Node>> d = new HashMap<>();
        int n = username.length;
        for (int i = 0; i < n; ++i) {
            String user = username[i];
            int ts = timestamp[i];
            String site = website[i];
            d.computeIfAbsent(user, k -> new ArrayList<>()).add(new Node(user, ts, site));
        }
        Map<String, Integer> cnt = new HashMap<>();
        for (var sites : d.values()) {
            int m = sites.size();
            Set<String> s = new HashSet<>();
            if (m > 2) {
                Collections.sort(sites, (a, b) -> a.ts - b.ts);
                for (int i = 0; i < m - 2; ++i) {
                    for (int j = i + 1; j < m - 1; ++j) {
                        for (int k = j + 1; k < m; ++k) {
                            s.add(sites.get(i).site + "," + sites.get(j).site + ","
                                + sites.get(k).site);
                        }
                    }
                }
            }
            for (String t : s) {
                cnt.put(t, cnt.getOrDefault(t, 0) + 1);
            }
        }
        int mx = 0;
        String t = "";
        for (var e : cnt.entrySet()) {
            if (mx < e.getValue() || (mx == e.getValue() && e.getKey().compareTo(t) < 0)) {
                mx = e.getValue();
                t = e.getKey();
            }
        }
        return Arrays.asList(t.split(","));
    }
}

class Node {
    String user;
    int ts;
    String site;

    Node(String user, int ts, String site) {
        this.user = user;
        this.ts = ts;
        this.site = site;
    }
}
```

```cpp
class Solution {
public:
    vector<string> mostVisitedPattern(vector<string>& username, vector<int>& timestamp, vector<string>& website) {
        unordered_map<string, vector<pair<int, string>>> d;
        int n = username.size();
        for (int i = 0; i < n; ++i) {
            auto user = username[i];
            int ts = timestamp[i];
            auto site = website[i];
            d[user].emplace_back(ts, site);
        }
        unordered_map<string, int> cnt;
        for (auto& [_, sites] : d) {
            int m = sites.size();
            unordered_set<string> s;
            if (m > 2) {
                sort(sites.begin(), sites.end());
                for (int i = 0; i < m - 2; ++i) {
                    for (int j = i + 1; j < m - 1; ++j) {
                        for (int k = j + 1; k < m; ++k) {
                            s.insert(sites[i].second + "," + sites[j].second + "," + sites[k].second);
                        }
                    }
                }
            }
            for (auto& t : s) {
                cnt[t]++;
            }
        }
        int mx = 0;
        string t;
        for (auto& [p, v] : cnt) {
            if (mx < v || (mx == v && t > p)) {
                mx = v;
                t = p;
            }
        }
        return split(t, ',');
    }

    vector<string> split(string& s, char c) {
        vector<string> res;
        stringstream ss(s);
        string t;
        while (getline(ss, t, c)) {
            res.push_back(t);
        }
        return res;
    }
};
```

```go
func mostVisitedPattern(username []string, timestamp []int, website []string) []string {
	d := map[string][]pair{}
	for i, user := range username {
		ts := timestamp[i]
		site := website[i]
		d[user] = append(d[user], pair{ts, site})
	}
	cnt := map[string]int{}
	for _, sites := range d {
		m := len(sites)
		s := map[string]bool{}
		if m > 2 {
			sort.Slice(sites, func(i, j int) bool { return sites[i].ts < sites[j].ts })
			for i := 0; i < m-2; i++ {
				for j := i + 1; j < m-1; j++ {
					for k := j + 1; k < m; k++ {
						s[sites[i].site+","+sites[j].site+","+sites[k].site] = true
					}
				}
			}
		}
		for t := range s {
			cnt[t]++
		}
	}
	mx, t := 0, ""
	for p, v := range cnt {
		if mx < v || (mx == v && p < t) {
			mx = v
			t = p
		}
	}
	return strings.Split(t, ",")
}

type pair struct {
	ts   int
	site string
}
```

<!-- tabs:end -->

<!-- end -->
