/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



package wsa;

/**
 *
 * @author vasilis
 */

class channel{  
    int key_field,   /*  our priority*/
        extra_key_field,   /* next slot priority*/
        forward_field,    /* next most popular message */
        backward_field,   /* the most popular message must be searched for next  */
        next_segment_field;   /* next segment field of same message type  */
    String message;  /* segment message */
    
    
    public static channel[] channelSet(int size){
        channel[] b_channel = new channel[size];
        for(int i=0; i<size; i++)
            b_channel[i] = new channel();
        return b_channel;
    }
    
}


class device{  
    int key_field,   /*  our priority*/
        forward_field,    /* next most popular message */
        backward_field,   /* the most popular message must be searched for next  */
        next_segment_field;   /* next segment field of same message type  */
    String message;  /* segment message */
    
    
    public static device[] deviceSet(int m_size){
        device[] m_device = new device[m_size];
        for(int i=0; i<m_size; i++)
            m_device[i] = new device();
        return m_device;
    }
    
}



public class Wsa {
    
    
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        
        /* arrangement of various message types into a broadcast channel  */
        /* we ignore the first field - the channel field,  as we implement one broadcast channel */
        
        
        int len = 32;
       
        channel[] b_channel =channel.channelSet(len);
        
             
        for(int i=0;i<len;i++){
           if((i % 2) == 0){
               b_channel[i].key_field = 1;  // black message type 
               b_channel[i].backward_field = 0; //Null as the black message is the most popular
               b_channel[i].next_segment_field = 2;
               if( (i % 8) == 0){
                   b_channel[i].message = "M1s1";
                   b_channel[i].forward_field = 1;
               }
               else if((i % 8) == 2){
                   b_channel[i].message = "M1s2";
                   b_channel[i].forward_field = 3;
               }
                else if((i % 8) == 4){
                   b_channel[i].message = "M1s3";
                   b_channel[i].forward_field = 1;
               }
               else if((i % 8) == 6){
                   b_channel[i].message = "M1s4";
                    b_channel[i].forward_field = 3;
               }
           }
           else if ((i % 4) == 1) {
               b_channel[i].key_field = 2;  //red
               if( (i % 8) == 1){
                   b_channel[i].message = "M2s1";
                   b_channel[i].next_segment_field = 4;
                   if(i==25){
                       b_channel[i].forward_field = 10;
                   }
                   else {
                       b_channel[i].forward_field = 2;
                   }
                   
                   if ( i == 9 || i == 17){
                       b_channel[i].backward_field = 0;
                       
                   }
                   else {
                       b_channel[i].backward_field = 1;
                   }
                   
                  
                   
                   
               }
               else if((i % 8) == 5){
                   b_channel[i].message = "M2s2";
                   b_channel[i].backward_field = 1;
               
               if( i == 21){
                   b_channel[i].next_segment_field = 8; 
                   b_channel[i].forward_field = 14;
               }
               else {
                   b_channel[i].next_segment_field = 4;
                   b_channel[i].forward_field = 6;
               }
               }
           }
           else if (( i % 8) == 3 && (i<20)) {
               b_channel[i].key_field = 3;  // blue
               b_channel[i].backward_field = 1;
                if( i == 3){
                   b_channel[i].message = "M3s1";
                   b_channel[i].forward_field = 4;
                   b_channel[i].next_segment_field = 8;
               }
               else if(i == 11){
                   b_channel[i].message = "M3s2";
                   b_channel[i].forward_field = 12;
                   b_channel[i].next_segment_field = 8;
               }
               else if(i == 19){
                   b_channel[i].message = "M3s1";
                   b_channel[i].forward_field = 4;
                   b_channel[i].next_segment_field = 24;
               } 
           }
           else if ( i == 7 || i == 23 ){
               b_channel[i].key_field = 4;  //green
               b_channel[i].backward_field = 1;
               b_channel[i].next_segment_field = 4;
               b_channel[i].message = "M4";
               if( i == 7){
                   b_channel[i].forward_field = 8;
               }
               else{
                   b_channel[i].forward_field = 4;
               }
               
           }
           else if ( i == 15 || i == 27 ){
               b_channel[i].key_field = 5;   //purple
               b_channel[i].backward_field = 1;
               if( i == 15){
                   b_channel[i].message = "M5s1";
                   b_channel[i].forward_field = 16;
                   b_channel[i].next_segment_field = 12;
               }
               else if(i == 27){
                   b_channel[i].message = "M5s2";
                   b_channel[i].forward_field = 4;
                   b_channel[i].next_segment_field = 20;
               }
           }
           else if ( i == 31 ){
               b_channel[i].key_field = 6;  //orange
               b_channel[i].message = "M6";
               b_channel[i].forward_field = 0;
               b_channel[i].backward_field = 1;
               b_channel[i].next_segment_field = 0;
                      
           }
        
        }
        
  
       
        for(int i=0;i<len;i++){
            
             if(i==31){
                 b_channel[i].extra_key_field = b_channel[0].key_field; 
             }
             else{
                 b_channel[i].extra_key_field = b_channel[i+1].key_field; 
             }
             
        }
        
        
        for(int i=0;i<len;i++){
            System.out.println("id=" + i + "key=" + b_channel[i].key_field + "extra field=" + b_channel[i].extra_key_field + "f_field=" + b_channel[i].forward_field + "b_field=" + b_channel[i].backward_field + "next_Seg=" + b_channel[i].next_segment_field + "mess=" + b_channel[i].message ); 
        }
        
        
             
        
        
         /* implementation of mobile devices - clients */    
         int m_len = 3;    
         device[] m_device =device.deviceSet(m_len);
         
         m_device[0].key_field = 50;
         m_device[0].forward_field = 0;
         m_device[0].backward_field = 0;      //client
         m_device[0].next_segment_field = 0;
         m_device[0].message = "";
         
         m_device[1].key_field = 1;
         m_device[1].forward_field = 0;
         m_device[1].backward_field = 0;      //client
         m_device[1].next_segment_field = 0;
         m_device[1].message = "";
         
         m_device[2].key_field = 2;
         m_device[2].forward_field = 0;
         m_device[2].backward_field = 0;      //client
         m_device[2].next_segment_field = 0;
         m_device[2].message = "";
         
         
         
         /* Implementation of Windmill algorith  */
         /* we assume that the mobile device is synchronized to the channel
            and there is only one channel  */
         int i,j;
         i=0;
         j=0;
         int flag = 0;
         int k = 0;
     
         do {
            i=0;
            do{
                if((m_device[j].key_field != b_channel[i].key_field) || (m_device[j].key_field != b_channel[i].extra_key_field)){
                    
                    System.out.println("Wait in doze mode for backward to be broadcasted");
                    try {
                        Thread.sleep((b_channel[i].backward_field));              //wait in doze mode until the backward most popular slot to be broadcasted in secs
                      } catch (InterruptedException ie) {
                           //Handle exception
                     }
                    int l = i;
                    
                    if((m_device[j].key_field == b_channel[b_channel[i].backward_field].key_field) || (m_device[j].key_field == b_channel[b_channel[i].backward_field].extra_key_field)){
                        
                        System.out.println("Message is found in backward broadcasted slot" + i);
                        //break ; 
                        flag = 1; 
                        
                    }else {
                        do{
                        
                         if((m_device[j].key_field != b_channel[b_channel[i].forward_field].key_field) || (m_device[j].key_field != b_channel[b_channel[i].forward_field].extra_key_field)){
                                System.out.println("Wait in doze mode for forward to be broadcasted");
                                try {
                                 Thread.sleep((b_channel[i].forward_field));              //wait in doze mode until the forward most popular slot to be broadcasted in secs
                                 } catch (InterruptedException ie) {
                                    //Handle exception
                                    } 
                         }else {
                            System.out.println("Message is found in forward broadcasted slot" + i );
                            //break ;
                            flag = 2;
                            
                         }
                         l++;   
                         }while(b_channel[l].forward_field != 0 );
                    
                    }
                    
                    if( i == len - 2 ){
                       System.out.println("The searched message is not found");
                       System.out.println("Message search finished");
                       
                    }
                    
                    
                } //if
                
                
                
         
                
               
                k = b_channel[i].next_segment_field;
                if( flag == 1  || flag == 2){
                while( k>0 ){
                    System.out.println("Wait in doze mode for next segment to be broadcasted");
                     try {
                        Thread.sleep(b_channel[k].next_segment_field);             
                      } catch (InterruptedException ie) {
                           //Handle exception
                     }
                     k--;
                }
                
                System.out.println("Message search finish");
                flag = 0;
                break;
                }
                
                //System.out.println("Message search finish");
              
             i++;   
            }while(i<len-1);
            j++; 
         }while(j<m_len);
         
                      
                      
                     
 

         
    
        
   }
    
}
