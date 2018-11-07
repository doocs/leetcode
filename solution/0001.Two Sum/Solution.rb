# @param {Integer[]} nums
# @param {Integer} target
# @return {Integer[]}
def two_sum(nums, target)
  nums.each_with_index do |x, idx|
    if nums.include? target - x
      return [idx, nums.index(target - x)] if nums.index(target - x) != idx
    end
    next
  end
end
