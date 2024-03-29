Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "c"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "d"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "g"
Output: "j"

Input:
letters = ["c", "f", "j"]
target = "j"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "k"
Output: "c"
Note:
letters has a length in range [2, 10000].
letters consists of lowercase letters, and contains at least 2 unique letters.
target is a lowercase letter.

-------------------------------------------------------
/**
binary search, find the first number that is larger than target 
*/
class Solution {
   public char nextGreatestLetter(char[] letters, char target) {
        if (target >= letters[letters.length-1]) {
          return letters[0];
        }
        int start = 0;
        int end = letters.length-1;

        while (start < end) {
          int mid = (start + end) / 2;
          if (letters[mid] > target) {
            end = mid;
          }
          else {
            start = mid + 1;
          }
        }
        return letters[start];
    }
}

---------------- educative 模板 -----------------------------------------------

   /*
find first element > k
*/
/*
find first element > k
*/
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (letters[mid] <= target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return start > letters.length - 1 ? letters[0] : letters[start];
    }
    
    // letter 可以比大小
    // private int num(char letter) {
    //     return letter - 'a';
    // } 
}
}
   
   
