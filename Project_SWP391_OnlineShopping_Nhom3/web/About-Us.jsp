<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Lien He</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <div id="contact-page" class="container">
            <div class="bg">
                <div class="row">    		
                    <div class="col-sm-12">    			   			
                        <h2 class="title text-center">Contact <strong>Us</strong></h2>    			    				    				
                        <div class="col-lg-9">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.482205851059!2d105.52509761440706!3d21.01338329368005!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x313454b32ca5086d%3A0xa3c62e29d8ab37e4!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgSMOgIE7hu5lp!5e0!3m2!1svi!2s!4v1596037780716!5m2!1svi!2s" width="1110" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                        </div>
                        
                        
                    </div>
                    </div>			 		
                </div>    	
                <div class="row">  	
                    <div class="col-sm-8">
                        <h2 class="title text-center">Get In Touch</h2>
                            <div class="status alert alert-success" style="display: none"></div>
                            <form action="${pageContext.request.contextPath}/About-Us"  method="POST">
                                <div class="form-group col-md-6">
                                    <input type="text" name="name" class="form-control"  placeholder="Name">
                                </div>
                                <div class="form-group col-md-6">
                                    <input type="email" name="email" class="form-control"  placeholder="Email">
                                </div>
                                <div class="form-group col-md-12">
                                    <input type="text" name="subject" class="form-control"  placeholder="Subject">
                                </div>
                                <div class="form-group col-md-12">
                                    <textarea name="message" id="imessage"   rows="10" cols="50" placeholder="Your Message Here"></textarea>
                                </div>                        
                                <div class="form-group col-md-12">
                                    <input type="submit" name="submit" class="btn btn-primary pull-right" value="Submit">
                                    
                                </div>
                            </form>
                        
                            
                        <div class="contact-form">
                            
                        
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="contact-info">
                            <h2 class="title text-center">Contact Info</h2>
                            <address>
                                <p>GearVN</p>
                                <p>Hoa Lac High Tech Park, Ha Noi</p>
                                <p>HaNoi VietNam</p>
                                <p>Email: CSKH@GEARVN.com</p>
                            </address>
                            <div class="social-networks">
                                <h2 class="title text-center">Social Networking</h2>
                                <ul>
                                    <li>
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-google-plus"></i></a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-youtube"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>    			
                </div>  
            </div>	
        </div><!--/#contact-page-->

        <jsp:include page="footer.jsp"></jsp:include>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <script type="text/javascript" src="js/gmaps.js"></script>
        <script src="js/contact.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>