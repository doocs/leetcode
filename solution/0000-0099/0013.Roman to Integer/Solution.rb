# @param {String} s
# @return {Integer}
def roman_to_int(s)
  d = {
      'I' => 1, 'V' => 5, 'X' => 10, 
      'L' => 50, 'C' => 100, 
      'D' => 500, 'M' => 1000
  }
  ans = 0
  len = s.length
  
  (0...len-1).each do |i|
      if d[s[i]] < d[s[i + 1]]
          ans -= d[s[i]]
      else
          ans += d[s[i]]
      end
  end
  
  ans += d[s[len - 1]]
  ans
end
