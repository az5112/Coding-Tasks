class Solution {
    class Mem {
        int value;
        int posA;
        Mem(int value, int posA) {
            this.value = value;
            this.posA = posA;
        }
    }

    interface Cmp {
        boolean compare (Comparable a, Comparable b);
    }

    class GreaterEqual implements Cmp {
        public boolean compare( Comparable a, Comparable b) {
            int r = a.compareTo(b);
            if( (r == 1) || (r == 0) ) return true;
            return false;
        };
    }

    class LessEqual implements Cmp {
        public boolean compare( Comparable a, Comparable b) {
            int r = a.compareTo(b);
            if( (r == 0) || (r == -1) ) return true;
            return false;
        };
    }

    public < T extends Cmp > int add_elem(T cmp, Mem a, Mem mem[], int begin, int end)
    {
        while( (end >= begin) && cmp.compare( mem[end].value, a.value) ) {
            end -= 1;
        }

        end += 1;
        mem[end] = a;
        return end;
    }


    public int solution(int K, int[] A) {
        final int max_int = 1_000_000_000;
        int N = A.length;

        Mem[] mini = new Mem[N+1];
        Mem[] maxi = new Mem[N+1];
        int mini_first = 0, mini_last = -1;
        int maxi_first = 0, maxi_last = -1;
        int j = 0;
        int result = 0;
        GreaterEqual ge = new GreaterEqual();
        LessEqual lt = new LessEqual();

        for( int i = 0; i < N; ++i ) {
            while( j < N ) {
                mini_last = add_elem(ge, new Mem(A[j], j), mini, mini_first, mini_last );
                maxi_last = add_elem(lt, new Mem(A[j], j), maxi, maxi_first, maxi_last );

                if( (maxi[maxi_first].value - mini[mini_first].value) <= K ) {
                    j += 1;
                }
                else {
                    break;
                }
            }

            result += (j - i);
            if(result >= max_int) return max_int;
            if(mini[mini_first].posA == i) mini_first += 1;
            if(maxi[maxi_first].posA == i) maxi_first += 1;
        }

        return result;
    }
}