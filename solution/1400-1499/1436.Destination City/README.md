# [1436. 旅行终点站](https://leetcode-cn.com/problems/destination-city)

[English Version](/solution/1400-1499/1436.Destination%20City/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一份旅游线路图，该线路图中的旅行线路用数组 <code>paths</code> 表示，其中 <code>paths[i] = [cityA<sub>i</sub>, cityB<sub>i</sub>]</code> 表示该线路将会从 <code>cityA<sub>i</sub></code> 直接前往 <code>cityB<sub>i</sub></code> 。请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市<em>。</em></p>

<p>题目数据保证线路图会形成一条不存在循环的线路，因此只会有一个旅行终点站。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>paths = [[&quot;London&quot;,&quot;New York&quot;],[&quot;New York&quot;,&quot;Lima&quot;],[&quot;Lima&quot;,&quot;Sao Paulo&quot;]]
<strong>输出：</strong>&quot;Sao Paulo&quot; 
<strong>解释：</strong>从 &quot;London&quot; 出发，最后抵达终点站 &quot;Sao Paulo&quot; 。本次旅行的路线是 &quot;London&quot; -&gt; &quot;New York&quot; -&gt; &quot;Lima&quot; -&gt; &quot;Sao Paulo&quot; 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>paths = [[&quot;B&quot;,&quot;C&quot;],[&quot;D&quot;,&quot;B&quot;],[&quot;C&quot;,&quot;A&quot;]]
<strong>输出：</strong>&quot;A&quot;
<strong>解释：</strong>所有可能的线路是：
&quot;D&quot; -&gt; &quot;B&quot; -&gt; &quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;B&quot; -&gt; &quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;C&quot; -&gt; &quot;A&quot;.&nbsp;
&quot;A&quot;.&nbsp;
显然，旅行终点站是 &quot;A&quot; 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>paths = [[&quot;A&quot;,&quot;Z&quot;]]
<strong>输出：</strong>&quot;Z&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 100</code></li>
	<li><code>paths[i].length == 2</code></li>
	<li><code>1 &lt;=&nbsp;cityA<sub>i</sub>.length,&nbsp;cityB<sub>i</sub>.length &lt;= 10</code></li>
	<li><code>cityA<sub>i&nbsp;</sub>!=&nbsp;cityB<sub>i</sub></code></li>
	<li>所有字符串均由大小写英文字母和空格字符组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def destCity(self, paths: List[List[str]]) -> str:
        mp = {a: b for a, b in paths}
        a =  paths[0][0]
        while mp.get(a):
            a = mp[a]
        return a
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String destCity(List<List<String>> paths) {
        Map<String, String> mp = new HashMap<>();
        for (List<String> path : paths) {
            mp.put(path.get(0), path.get(1));
        }
        String a = paths.get(0).get(0);
        while (mp.get(a) != null) {
            a = mp.get(a);
        }
        return a;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string destCity(vector<vector<string>>& paths) {
        unordered_map<string, string> mp;
        for (auto& path : paths) mp[path[0]] = path[1];
        string a = paths[0][0];
        while (mp.find(a) != mp.end()) a = mp[a];
        return a;
    }
};
```

### **Go**

```go
func destCity(paths [][]string) string {
	mp := make(map[string]string)
	for _, path := range paths {
		mp[path[0]] = path[1]
	}
	a := paths[0][0]
	for true {
		if _, ok := mp[a]; !ok {
			return a
		}
		a = mp[a]
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
