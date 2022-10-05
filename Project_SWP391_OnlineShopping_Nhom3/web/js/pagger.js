/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function render(id,pageindex, totalpage, gap)
{
    var container = document.getElementById(id);
    var content = "";
    if(pageindex > gap+1 )
        content+= "<a class=\"btn btn-dark\" href=\"list?page=1\">First</a>";
    
    for(var i = pageindex - gap;i<pageindex;i++)
        if(i>0)
            content+= "<a class=\"btn btn-dark\" href=\"list?page="+i+"\">"+i+"</a>";
            
    
    content += "<span class=\"btn btn-dark\">"+pageindex+"</span>";
    
    for(var i = pageindex + 1;i<=pageindex+gap;i++)
        if(i<=totalpage)
            content+= "<a class=\"btn btn-dark\" href=\"list?page="+i+"\">"+i+"</a>";
    
    if(pageindex <totalpage - gap )
        content+= "<a class=\"btn btn-dark\" href=\"list?page="+totalpage+"\">Last</a>";
    
    container.innerHTML = content;
}

