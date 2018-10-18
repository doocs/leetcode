# @param {String} s
# @return {Integer}
def roman_to_int(s)
  hash = Hash[
      'I' => 1,
      'V' => 5,
      'X' => 10,
      'L' => 50,
      'C' => 100,
      'D' => 500,
      'M' => 1000,
      'IV' => 4,
      'IX' => 9,
      'XL' => 40,
      'XC' => 90,
      'CD' => 400,
      'CM' => 900
  ]
  res = 0
  i = 0
  while i < s.length
    if i < s.length - 1 && !hash[s[i..i+1]].nil?
      res += hash[s[i..i+1]]
      i += 2
    else
      res += hash[s[i]]
      i += 1
    end
  end

  res
end
