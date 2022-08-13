# [1773. 统计匹配检索规则的物品数量](https://leetcode.cn/problems/count-items-matching-a-rule)

[English Version](/solution/1700-1799/1773.Count%20Items%20Matching%20a%20Rule/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>items</code> ，其中 <code>items[i] = [type<sub>i</sub>, color<sub>i</sub>, name<sub>i</sub>]</code> ，描述第 <code>i</code> 件物品的类型、颜色以及名称。</p>

<p>另给你一条由两个字符串 <code>ruleKey</code> 和 <code>ruleValue</code> 表示的检索规则。</p>

<p>如果第 <code>i</code> 件物品能满足下述条件之一，则认为该物品与给定的检索规则 <strong>匹配</strong> ：</p>

<ul>
	<li><code>ruleKey == "type"</code> 且 <code>ruleValue == type<sub>i</sub></code> 。</li>
	<li><code>ruleKey == "color"</code> 且 <code>ruleValue == color<sub>i</sub></code> 。</li>
	<li><code>ruleKey == "name"</code> 且 <code>ruleValue == name<sub>i</sub></code> 。</li>
</ul>

<p>统计并返回 <strong>匹配检索规则的物品数量</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
<strong>输出：</strong>1
<strong>解释：</strong>只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
<strong>输出：</strong>2
<strong>解释：</strong>只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和 ["phone","gold","iphone"] 。注意，["computer","silver","phone"] 未匹配检索规则。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= items.length <= 10<sup>4</sup></code></li>
	<li><code>1 <= type<sub>i</sub>.length, color<sub>i</sub>.length, name<sub>i</sub>.length, ruleValue.length <= 10</code></li>
	<li><code>ruleKey</code> 等于 <code>"type"</code>、<code>"color"</code> 或 <code>"name"</code></li>
	<li>所有字符串仅由小写字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countMatches(self, items: List[List[str]], ruleKey: str, ruleValue: str) -> int:
        count = 0
        m = {'type': 0, 'color': 1, 'name': 2}
        return sum([item[m[ruleKey]] == ruleValue for item in items])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        for (List<String> item : items) {
            String t = item.get(0), c = item.get(1), n = item.get(2);
            if ("type".equals(ruleKey) && t.equals(ruleValue)) {
                ++count;
            } else if ("color".equals(ruleKey) && c.equals(ruleValue)) {
                ++count;
            } else if ("name".equals(ruleKey) && n.equals(ruleValue)) {
                ++count;
            }
        }
        return count;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
