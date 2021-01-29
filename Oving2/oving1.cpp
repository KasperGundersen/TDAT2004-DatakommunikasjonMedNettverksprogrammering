#include <iostream>
#include <math.h>
#include <vector>
#include <thread>
#include <algorithm> // std::sort
using namespace std;
int main()
{
    int start, end, amtThreads, fnd = 0;
    vector<thread> threads;
    vector<int> primes = vector<int>();
    int interval = (end - start) / amtThreads;

    cout << "\n\n Find prime number within a range:\n";
    cout << "--------------------------------------\n";
    cout << " Input number for starting range: ";
    cin >> start;
    cout << " Input number for ending range: ";
    cin >> end;
    cout << " Amount of threads: ";
    cin >> amtThreads;
    if (start < 2)
        start = 2;
    for (int i = 0; i < amtThreads; i++)
    {
        threads.emplace_back([i, interval, amtThreads, start, end, &primes] {
            int low = start + i * interval;
            int high = start + i * interval + interval;
            if (i == amtThreads - 1)
            {
                high = end;
            }
            int flag;
            while (low < high)
            {
                flag = 0;
                for (int i = 2; i <= sqrt(low); ++i)
                {
                    if (low % i == 0 || low % 2 == 0)
                    {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0)
                {
                    primes.push_back(low);
                }
                ++low;
            }
        });
    }
    for (auto &thread : threads)
        thread.join();
    cout << "\n The prime numbers between " << start << " and " << end << " are:" << endl;
    sort(primes.begin(), primes.end());
    for (int prime : primes)
    {
        cout << to_string(prime) + " - ";
    }
    cout << "\n\nThe total number of prime numbers between " << start << " to " << end << " is: " << primes.size();
}