class Solution(object):
    def countAndSay(self, n):
        s = '1'   #初始化第一个数字    
        for i in range(n-1):  #n-1个数      
            temp = ''   #一个空数列
            num = s[0]  #s的第一位数字
            count = 0    #当前数字的个数
            
            for j in s:   #遍历s中的每个字母
                if num == j:     #如果这个字母和num一样
                    count += 1     #计数+1
                else:
                    temp += str(count)+str(num)  #存下“几”个“什么数”
                    num = j   #num改成当前数字
                    count = 1   #计数回到1
            temp += str(count)+str(num)  #加的是最后一个
            s = temp
        return s
