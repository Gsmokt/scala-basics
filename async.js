const func = async () => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve("hello")
    },2000)
  })
}

const func2 = async () => {
    let a = await func()
    console.log("still waiting...")
    if(a) {
        return await func3(a)
    }
    return func2()
}

const func3 = async (arg) => {
    const response = await fetch(`${arg}`)
    const json = await response.json()
    return json
}


