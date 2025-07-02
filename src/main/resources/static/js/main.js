document.addEventListener('DOMContentLoaded', async () => {
  try {
    const response = await fetch('/api/verify', {
      method: 'GET',
      credentials: 'include'
    })

    if (!response.ok) {
      window.location.href = '/login'
      return
    }

    const user = await response.json()

    document.getElementById('name').textContent = user.name
    document.getElementById('email').textContent = user.email
    document.getElementById('bio').textContent = user.bio

    document.getElementById('logout').addEventListener('click', async () => {
      try {
        const logout_response = await fetch('/api/logout', {
            method: 'POST',
            credentials: 'include'
        })

        alert(logout_response.message)

        window.location.href = '/login'
      } catch (err_1) {
        alert(err_1.message)
      }
    })

  } catch (err) {
    console.log(err.message)
  }
})

document.getElementById('post').addEventListener('submit', async () => {
  try {
    const title = document.getElementById('title').value.trim()
    const content = document.getElementById('content').value.trim()
    
    const data = {title, content}

    const response = await fetch('/api/posts', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data),
      credentials: 'include'
    })

    if (!response.ok) {
      const errMsg = await response.text()
      alert(errMsg)
    }

    alert('Success')
  } catch (err) {
    console.log(err.message)
  }
})
