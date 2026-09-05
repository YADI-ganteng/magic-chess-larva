#!/bin/bash
# Download script untuk assets yang gagal didownload
# Jalankan di terminal: bash download_assets.sh

echo "Downloading missing assets..."

# Function untuk download dari Google Drive
download_drive() {
    local file_id=$1
    local output_path=$2
    
    mkdir -p "$(dirname "$output_path")"
    
    if [ ! -f "$output_path" ] || [ ! -s "$output_path" ]; then
        echo "Downloading: $output_path"
        gdown "$file_id" -O "$output_path" 2>/dev/null ||         curl -L "https://drive.google.com/uc?export=download&id=$file_id" -o "$output_path" 2>/dev/null
        
        if [ -f "$output_path" ] && [ -s "$output_path" ]; then
            echo "✅ Downloaded: $output_path"
        else
            echo "❌ Failed: $output_path"
        fi
    else
        echo "⏭️ Skipped (exists): $output_path"
    fi
}


echo "Done!"
