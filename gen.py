

def fib():
    ar = [0,1]
    def wrapper(n):
        nonlocal ar
        for i in range(n):
            first = ar[1]
            ar[1] = ar[0] + ar[1]
            ar[0] = first
            yield ar[1]

    return wrapper

func = fib()
for i in func(100):
    print(i)