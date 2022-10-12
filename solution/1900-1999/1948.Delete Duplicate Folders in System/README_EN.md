# [1948. Delete Duplicate Folders in System](https://leetcode.com/problems/delete-duplicate-folders-in-system)

[中文文档](/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/README.md)

## Description

<p>Due to a bug, there are many duplicate folders in a file system. You are given a 2D array <code>paths</code>, where <code>paths[i]</code> is an array representing an absolute path to the <code>i<sup>th</sup></code> folder in the file system.</p>

<ul>
	<li>For example, <code>[&quot;one&quot;, &quot;two&quot;, &quot;three&quot;]</code> represents the path <code>&quot;/one/two/three&quot;</code>.</li>
</ul>

<p>Two folders (not necessarily on the same level) are <strong>identical</strong> if they contain the <strong>same non-empty</strong> set of identical subfolders and underlying subfolder structure. The folders <strong>do not</strong> need to be at the root level to be identical. If two or more folders are <strong>identical</strong>, then <strong>mark</strong> the folders as well as all their subfolders.</p>

<ul>
	<li>For example, folders <code>&quot;/a&quot;</code> and <code>&quot;/b&quot;</code> in the file structure below are identical. They (as well as their subfolders) should <strong>all</strong> be marked:

    <ul>
    	<li><code>/a</code></li>
    	<li><code>/a/x</code></li>
    	<li><code>/a/x/y</code></li>
    	<li><code>/a/z</code></li>
    	<li><code>/b</code></li>
    	<li><code>/b/x</code></li>
    	<li><code>/b/x/y</code></li>
    	<li><code>/b/z</code></li>
    </ul>
    </li>
    <li>However, if the file structure also included the path <code>&quot;/b/w&quot;</code>, then the folders <code>&quot;/a&quot;</code> and <code>&quot;/b&quot;</code> would not be identical. Note that <code>&quot;/a/x&quot;</code> and <code>&quot;/b/x&quot;</code> would still be considered identical even with the added folder.</li>

</ul>

<p>Once all the identical folders and their subfolders have been marked, the file system will <strong>delete</strong> all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.</p>

<p>Return <em>the 2D array </em><code>ans</code> <em>containing the paths of the <strong>remaining</strong> folders after deleting all the marked folders. The paths may be returned in <strong>any</strong> order</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder1.jpg" style="width: 200px; height: 218px;" />
<pre>
<strong>Input:</strong> paths = [[&quot;a&quot;],[&quot;c&quot;],[&quot;d&quot;],[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;b&quot;],[&quot;d&quot;,&quot;a&quot;]]
<strong>Output:</strong> [[&quot;d&quot;],[&quot;d&quot;,&quot;a&quot;]]
<strong>Explanation:</strong> The file structure is as shown.
Folders &quot;/a&quot; and &quot;/c&quot; (and their subfolders) are marked for deletion because they both contain an empty
folder named &quot;b&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder2.jpg" style="width: 200px; height: 355px;" />
<pre>
<strong>Input:</strong> paths = [[&quot;a&quot;],[&quot;c&quot;],[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;b&quot;],[&quot;a&quot;,&quot;b&quot;,&quot;x&quot;],[&quot;a&quot;,&quot;b&quot;,&quot;x&quot;,&quot;y&quot;],[&quot;w&quot;],[&quot;w&quot;,&quot;y&quot;]]
<strong>Output:</strong> [[&quot;c&quot;],[&quot;c&quot;,&quot;b&quot;],[&quot;a&quot;],[&quot;a&quot;,&quot;b&quot;]]
<strong>Explanation: </strong>The file structure is as shown. 
Folders &quot;/a/b/x&quot; and &quot;/w&quot; (and their subfolders) are marked for deletion because they both contain an empty folder named &quot;y&quot;.
Note that folders &quot;/a&quot; and &quot;/c&quot; are identical after the deletion, but they are not deleted because they were not marked beforehand.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1948.Delete%20Duplicate%20Folders%20in%20System/images/lc-dupfolder3.jpg" style="width: 200px; height: 201px;" />
<pre>
<strong>Input:</strong> paths = [[&quot;a&quot;,&quot;b&quot;],[&quot;c&quot;,&quot;d&quot;],[&quot;c&quot;],[&quot;a&quot;]]
<strong>Output:</strong> [[&quot;c&quot;],[&quot;c&quot;,&quot;d&quot;],[&quot;a&quot;],[&quot;a&quot;,&quot;b&quot;]]
<strong>Explanation:</strong> All folders are unique in the file system.
Note that the returned array can be in a different order as the order does not matter.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paths.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= paths[i].length &lt;= 500</code></li>
	<li><code>1 &lt;= paths[i][j].length &lt;= 10</code></li>
	<li><code>1 &lt;= sum(paths[i][j].length) &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>path[i][j]</code> consists of lowercase English letters.</li>
	<li>No two paths lead to the same folder.</li>
	<li>For any folder not at the root level, its parent folder will also be in the input.</li>
</ul>

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
