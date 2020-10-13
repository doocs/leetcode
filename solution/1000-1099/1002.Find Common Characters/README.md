# [1002. 查找常用字符](https://leetcode-cn.com/problems/find-common-characters)

[English Version](/solution/1000-1099/1002.Find%20Common%20Characters/README_EN.md)

## 题目描述
<!-- 这里写题目描述 -->
<p>给定仅有小写字母组成的字符串数组 <code>A</code>，返回列表中的每个字符串中都显示的全部字符（<strong>包括重复字符</strong>）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。</p>

<p>你可以按任意顺序返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[&quot;bella&quot;,&quot;label&quot;,&quot;roller&quot;]
<strong>输出：</strong>[&quot;e&quot;,&quot;l&quot;,&quot;l&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[&quot;cool&quot;,&quot;lock&quot;,&quot;cook&quot;]
<strong>输出：</strong>[&quot;c&quot;,&quot;o&quot;]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 100</code></li>
	<li><code>1 &lt;= A[i].length &lt;= 100</code></li>
	<li><code>A[i][j]</code> 是小写字母</li>
</ol>



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

### **Go**
```go
func commonChars(A []string) []string {
    if len(A) == 0 {
        return []string{}
    }
    res := make([]int, 26)
    //以第一个字符串为基准，先统计出现次数
    for _, c := range A[0] {
        res[c - 'a']++
    }
    for i := 1; i < len(A); i++ {
        tmp := make([]int, 26)
        //统计后续每个字符串的字符出现次数
        for _, c := range A[i] {
            tmp[c - 'a']++
        }
        //比较，取小
        for j := 0; j < 26; j++ {
            res[j] = getMin(res[j], tmp[j])
        }
    }
    //遍历res,取出字符转换为string数组元素
    result := make([]string,0)
    for i := 0; i < len(res); i++ {
        if res[i] > 0 {
            for j := 0; j < res[i]; j++ {
                result = append(result, string('a' + i))
            }
        }
    }
    return result
}

func getMin(a,b int) int {
    if a > b{
        return b
    }
    return a
}

```

<!-- tabs:end -->
<!-- tabs:end -->