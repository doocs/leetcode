# [514. Freedom Trail](https://leetcode.com/problems/freedom-trail)

## Description
<p>In the video game Fallout 4, the quest &quot;Road to Freedom&quot; requires players to reach a metal dial called the &quot;Freedom Trail Ring&quot;, and use the dial to spell a specific keyword in order to open the door.</p>



<p>Given a string <b>ring</b>, which represents the code engraved on the outer ring and another string <b>key</b>, which represents the keyword needs to be spelled. You need to find the <b>minimum</b> number of steps in order to spell all the characters in the keyword.</p>



<p>Initially, the first character of the <b>ring</b> is aligned at 12:00 direction. You need to spell all the characters in the string <b>key</b> one by one by rotating the ring clockwise or anticlockwise to make each character of the string <b>key</b> aligned at 12:00 direction and then by pressing the center button.</p>



<p>At the stage of rotating the ring to spell the key character <b>key[i]</b>:</p>



<ol>

	<li>You can rotate the <b>ring</b> clockwise or anticlockwise <b>one place</b>, which counts as 1 step. The final purpose of the rotation is to align one of the string <b>ring&#39;s</b> characters at the 12:00 direction, where this character must equal to the character <b>key[i]</b>.</li>

	<li>If the character <b>key[i]</b> has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you&#39;ve finished all the spelling.</li>

</ol>



<p><b>Example:</b></p>



<center><img src="https://assets.leetcode.com/uploads/2018/10/22/ring.jpg" style="width: 26%;" /></center>

&nbsp;



<pre>

<b>Input:</b> ring = &quot;godding&quot;, key = &quot;gd&quot;

<b>Output:</b> 4

<b>Explanation:</b>

For the first key character &#39;g&#39;, since it is already in place, we just need 1 step to spell this character. 

For the second key character &#39;d&#39;, we need to rotate the ring &quot;godding&quot; anticlockwise by two steps to make it become &quot;ddinggo&quot;.

Also, we need 1 more step for spelling.

So the final output is 4.

</pre>



<p><b>Note:</b></p>



<ol>

	<li>Length of both ring and <b>key</b> will be in range 1 to 100.</li>

	<li>There are only lowercase letters in both strings and might be some duplcate characters in both strings.</li>

	<li>It&#39;s guaranteed that string <b>key</b> could always be spelled by rotating the string <b>ring</b>.</li>

</ol>




## Solutions


<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java

```

### **...**
```

```

<!-- tabs:end -->