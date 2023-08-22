import './NavBar.css';
import React from 'react'
import royaltheorem from './royaltheorem.svg'

export default function NavBar() {
  return (
    <div class="navBar">
        <div class="logo">
            <img src={royaltheorem}/>

        </div>
        <div class="mlogo">
            <svg height="200px" width="200px">

                <text class="r" x="40" y="170" fill="red">R</text>
                <text class="t" x="50" y="175" >T</text>
            </svg>
        </div>
        <div class="menu">
            <ul>
                <li>Home</li>
                <li>About</li>
                <li>Help</li>
                <li>Account</li>
                
            </ul>
        </div>
    </div>
  )
}
