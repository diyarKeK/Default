const like = document.getElementById('like')
const comment = document.getElementById('comment')
const share = document.getElementById('share')


document.getElementById('light-theme').addEventListener('click', () => {
    like.src = 'images/like-light.png'
    comment.src = 'images/comment-light.png'
    share.src = 'images/share-light.png'
})

document.getElementById('dark-theme').addEventListener('click', () => {
    like.src = 'images/like-dark.png'
    comment.src = 'images/comment-dark.png'
    share.src = 'images/share-dark.png'
})