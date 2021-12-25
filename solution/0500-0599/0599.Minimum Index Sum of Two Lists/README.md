# [599. 两个列表的最小索引总和](https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists)

[English Version](/solution/0500-0599/0599.Minimum%20Index%20Sum%20of%20Two%20Lists/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。</p>

<p>你需要帮助他们用<strong>最少的索引和</strong>找出他们<strong>共同喜爱的餐厅</strong>。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong>
[&quot;Shogun&quot;, &quot;Tapioca Express&quot;, &quot;Burger King&quot;, &quot;KFC&quot;]
[&quot;Piatti&quot;, &quot;The Grill at Torrey Pines&quot;, &quot;Hungry Hunter Steakhouse&quot;, &quot;Shogun&quot;]
<strong>输出:</strong> [&quot;Shogun&quot;]
<strong>解释:</strong> 他们唯一共同喜爱的餐厅是&ldquo;Shogun&rdquo;。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong>
[&quot;Shogun&quot;, &quot;Tapioca Express&quot;, &quot;Burger King&quot;, &quot;KFC&quot;]
[&quot;KFC&quot;, &quot;Shogun&quot;, &quot;Burger King&quot;]
<strong>输出:</strong> [&quot;Shogun&quot;]
<strong>解释:</strong> 他们共同喜爱且具有最小索引和的餐厅是&ldquo;Shogun&rdquo;，它有最小的索引和1(0+1)。
</pre>

<p><strong>提示:</strong></p>

<ol>
	<li>两个列表的长度范围都在&nbsp;[1, 1000]内。</li>
	<li>两个列表中的字符串的长度将在[1，30]的范围内。</li>
	<li>下标从0开始，到列表的长度减1。</li>
	<li>两个列表都没有重复的元素。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先用哈希表 mp 记录 list2 的每个字符串以及对应的下标。初始化最小的索引和 mi = 2000，ans 表示结果列表，初始值为空。

遍历 list1 每个字符串 v，若 v 在 mp 中，则计算两个字符串的索引和 t，并更新 ans 和 mi。

最后返回 ans 即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findRestaurant(self, list1: List[str], list2: List[str]) -> List[str]:
        ans = []
        mp = {v: i for i, v in enumerate(list2)}
        mi = 2000
        for i, v in enumerate(list1):
            if v in mp:
                t = i + mp[v]
                if t < mi:
                    mi = t
                    ans = [v]
                elif t == mi:
                    ans.append(v)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < list2.length; ++i) {
            mp.put(list2[i], i);
        }
        List<String> ans = new ArrayList<>();
        int mi = 2000;
        for (int i = 0; i < list1.length; ++i) {
            if (mp.containsKey(list1[i])) {
                int t = i + mp.get(list1[i]);
                if (t < mi) {
                    ans = new ArrayList<>();
                    ans.add(list1[i]);
                    mi = t;
                } else if (t == mi) {
                    ans.add(list1[i]);
                }
            }
        }
        return ans.toArray(new String[0]);
    }
}

```

### **C++**

```cpp
class Solution {
public:
    vector<string> findRestaurant(vector<string>& list1, vector<string>& list2) {
        unordered_map<string, int> mp;
        for (int i = 0; i < list2.size(); ++i) mp[list2[i]] = i;
        int mi = 2000;
        vector<string> ans;
        for (int i = 0; i < list1.size(); ++i)
        {
            if (mp.count(list1[i]))
            {
                int t = i + mp[list1[i]];
                if (t < mi)
                {
                    ans.clear();
                    ans.push_back(list1[i]);
                    mi = t;
                }
                else if (t == mi)
                {
                    ans.push_back(list1[i]);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```cpp
func findRestaurant(list1 []string, list2 []string) []string {
	mp := make(map[string]int)
	for i, v := range list2 {
		mp[v] = i
	}
	mi := 2000
	var ans []string
	for i, v := range list1 {
		if _, ok := mp[v]; ok {
			t := i + mp[v]
			if t < mi {
				ans = []string{v}
				mi = t
			} else if t == mi {
				ans = append(ans, v)
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
